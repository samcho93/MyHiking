package com.myhiking.app.data.repository

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.exifinterface.media.ExifInterface
import com.myhiking.app.data.model.DevicePhoto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PhotoRepository(private val context: Context) {

    data class PhotoGeoInfo(
        val latitude: Double,
        val longitude: Double,
        val altitude: Double
    )

    /**
     * 기기 사진 스캔. excludeIds에 있는 사진은 EXIF 파싱을 건너뜀 (증분 스캔)
     * @param excludeIds 이미 캐시된 사진 ID (EXIF 파싱 생략)
     * @return 새로 스캔된 사진만 반환 (캐시된 사진 제외)
     */
    suspend fun scanGeotaggedPhotos(
        excludeIds: Set<Long> = emptySet()
    ): List<DevicePhoto> = withContext(Dispatchers.IO) {
        val photos = mutableListOf<DevicePhoto>()

        val collection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
        } else {
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        }

        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.DATE_TAKEN,
            MediaStore.Images.Media.SIZE
        )

        val sortOrder = "${MediaStore.Images.Media.DATE_TAKEN} DESC"

        context.contentResolver.query(
            collection, projection, null, null, sortOrder
        )?.use { cursor ->
            val idCol = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val nameCol = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
            val dateCol = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_TAKEN)
            val sizeCol = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)

            while (cursor.moveToNext()) {
                val id = cursor.getLong(idCol)

                // 이미 캐시된 사진은 EXIF 파싱 건너뛰기
                if (id in excludeIds) continue

                val name = cursor.getString(nameCol) ?: "unknown"
                val dateTaken = cursor.getLong(dateCol)
                val size = cursor.getLong(sizeCol)

                val contentUri = ContentUris.withAppendedId(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id
                )

                val geoInfo = getPhotoGeoInfo(contentUri)
                if (geoInfo != null) {
                    photos.add(
                        DevicePhoto(
                            id = id,
                            uri = contentUri,
                            latitude = geoInfo.latitude,
                            longitude = geoInfo.longitude,
                            altitude = geoInfo.altitude,
                            dateTaken = dateTaken,
                            displayName = name,
                            size = size
                        )
                    )
                }
            }
        }

        photos
    }

    /**
     * MediaStore에 현재 존재하는 모든 사진 ID 반환 (삭제된 사진 감지용)
     */
    suspend fun getAllPhotoIds(): Set<Long> = withContext(Dispatchers.IO) {
        val ids = mutableSetOf<Long>()
        val collection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
        } else {
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        }

        context.contentResolver.query(
            collection,
            arrayOf(MediaStore.Images.Media._ID),
            null, null, null
        )?.use { cursor ->
            val idCol = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            while (cursor.moveToNext()) {
                ids.add(cursor.getLong(idCol))
            }
        }
        ids
    }

    /**
     * 특정 사진 ID 목록의 메타데이터를 MediaStore에서 조회 (위치정보 없는 사진용)
     * GPS 데이터가 없어도 DevicePhoto로 반환 (lat=0, lng=0, alt=0)
     */
    suspend fun scanPhotosByIds(
        ids: Set<Long>
    ): List<DevicePhoto> = withContext(Dispatchers.IO) {
        if (ids.isEmpty()) return@withContext emptyList()

        val photos = mutableListOf<DevicePhoto>()
        val collection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
        } else {
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        }

        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.DATE_TAKEN,
            MediaStore.Images.Media.SIZE
        )

        context.contentResolver.query(
            collection, projection, null, null, null
        )?.use { cursor ->
            val idCol = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val nameCol = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
            val dateCol = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_TAKEN)
            val sizeCol = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)

            while (cursor.moveToNext()) {
                val id = cursor.getLong(idCol)
                if (id !in ids) continue

                val name = cursor.getString(nameCol) ?: "unknown"
                val dateTaken = cursor.getLong(dateCol)
                val size = cursor.getLong(sizeCol)

                val contentUri = ContentUris.withAppendedId(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id
                )

                // GPS 정보가 있으면 사용, 없으면 (0,0,0)
                val geoInfo = getPhotoGeoInfo(contentUri)
                photos.add(
                    DevicePhoto(
                        id = id,
                        uri = contentUri,
                        latitude = geoInfo?.latitude ?: 0.0,
                        longitude = geoInfo?.longitude ?: 0.0,
                        altitude = geoInfo?.altitude ?: 0.0,
                        dateTaken = dateTaken,
                        displayName = name,
                        size = size
                    )
                )
            }
        }
        photos
    }

    private fun getPhotoGeoInfo(uri: Uri): PhotoGeoInfo? {
        return try {
            val photoUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                MediaStore.setRequireOriginal(uri)
            } else {
                uri
            }

            context.contentResolver.openInputStream(photoUri)?.use { inputStream ->
                val exif = ExifInterface(inputStream)
                val latLng = FloatArray(2)
                if (exif.getLatLong(latLng)) {
                    val altitude = exif.getAltitude(0.0)
                    PhotoGeoInfo(
                        latitude = latLng[0].toDouble(),
                        longitude = latLng[1].toDouble(),
                        altitude = altitude
                    )
                } else {
                    null
                }
            }
        } catch (e: Exception) {
            null
        }
    }
}
