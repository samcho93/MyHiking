package com.myhiking.app.data.repository

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.exifinterface.media.ExifInterface
import com.myhiking.app.data.model.DevicePhoto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PhotoRepository(private val context: Context) {

    companion object {
        private const val TAG = "PhotoRepository"
    }

    data class PhotoGeoInfo(
        val latitude: Double,
        val longitude: Double,
        val altitude: Double
    )

    /**
     * 기기의 모든 사진 폴더 목록 조회 (폴더명 → 사진 수)
     */
    suspend fun getPhotoFolders(): List<Pair<String, Int>> = withContext(Dispatchers.IO) {
        val folderCounts = mutableMapOf<String, Int>()

        val collection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
        } else {
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        }

        context.contentResolver.query(
            collection,
            arrayOf(MediaStore.Images.Media.DATA),
            null, null, null
        )?.use { cursor ->
            val dataCol = cursor.getColumnIndex(MediaStore.Images.Media.DATA)
            if (dataCol < 0) return@use

            while (cursor.moveToNext()) {
                val filePath = cursor.getString(dataCol) ?: continue
                val folder = extractFolder(filePath)
                folderCounts[folder] = (folderCounts[folder] ?: 0) + 1
            }
        }

        folderCounts.entries
            .sortedByDescending { it.value }
            .map { it.key to it.value }
    }

    /**
     * 기기 사진 스캔. excludeIds에 있는 사진은 EXIF 파싱을 건너뜀 (증분 스캔)
     * @param excludeIds 이미 캐시된 사진 ID (EXIF 파싱 생략)
     * @param allowedFolders 스캔할 폴더 목록 (null이면 전체 스캔)
     * @param onProgress 스캔 진행 상태 콜백 (폴더명, 스캔 건수, GPS 발견 건수)
     * @return 새로 스캔된 사진만 반환 (캐시된 사진 제외)
     */
    suspend fun scanGeotaggedPhotos(
        excludeIds: Set<Long> = emptySet(),
        allowedFolders: Set<String>? = null,
        onProgress: ((folder: String, scanned: Int, found: Int) -> Unit)? = null
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
            MediaStore.Images.Media.SIZE,
            MediaStore.Images.Media.DATA  // 파일 경로 (폴백용)
        )

        val sortOrder = "${MediaStore.Images.Media.DATE_TAKEN} DESC"

        var totalScanned = 0
        var gpsFound = 0
        var gpsFailed = 0
        var lastFolder = ""

        context.contentResolver.query(
            collection, projection, null, null, sortOrder
        )?.use { cursor ->
            val idCol = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val nameCol = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
            val dateCol = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_TAKEN)
            val sizeCol = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)
            val dataCol = cursor.getColumnIndex(MediaStore.Images.Media.DATA)

            while (cursor.moveToNext()) {
                val id = cursor.getLong(idCol)

                // 이미 캐시된 사진은 EXIF 파싱 건너뛰기
                if (id in excludeIds) continue

                totalScanned++

                val name = cursor.getString(nameCol) ?: "unknown"
                val dateTaken = cursor.getLong(dateCol)
                val size = cursor.getLong(sizeCol)
                val filePath = if (dataCol >= 0) cursor.getString(dataCol) else null

                // 현재 폴더 추출 및 진행 상황 보고
                val currentFolder = extractFolder(filePath)
                if (currentFolder != lastFolder) {
                    lastFolder = currentFolder
                    onProgress?.invoke(currentFolder, totalScanned, gpsFound)
                }

                // 허용된 폴더가 아니면 건너뛰기
                if (allowedFolders != null && currentFolder !in allowedFolders) continue

                val contentUri = ContentUris.withAppendedId(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id
                )

                val geoInfo = getPhotoGeoInfo(contentUri, filePath)
                if (geoInfo != null) {
                    gpsFound++
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
                } else {
                    gpsFailed++
                }
            }
        }

        // 스캔 완료 시 최종 상태 보고
        onProgress?.invoke("", totalScanned, gpsFound)

        Log.i(TAG, "스캔 완료: 총 ${totalScanned}장 검사, GPS 발견 ${gpsFound}장, GPS 없음 ${gpsFailed}장")
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
            MediaStore.Images.Media.SIZE,
            MediaStore.Images.Media.DATA
        )

        context.contentResolver.query(
            collection, projection, null, null, null
        )?.use { cursor ->
            val idCol = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val nameCol = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
            val dateCol = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_TAKEN)
            val sizeCol = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)
            val dataCol = cursor.getColumnIndex(MediaStore.Images.Media.DATA)

            while (cursor.moveToNext()) {
                val id = cursor.getLong(idCol)
                if (id !in ids) continue

                val name = cursor.getString(nameCol) ?: "unknown"
                val dateTaken = cursor.getLong(dateCol)
                val size = cursor.getLong(sizeCol)
                val filePath = if (dataCol >= 0) cursor.getString(dataCol) else null

                val contentUri = ContentUris.withAppendedId(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id
                )

                // GPS 정보가 있으면 사용, 없으면 (0,0,0)
                val geoInfo = getPhotoGeoInfo(contentUri, filePath)
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

    /**
     * 사진의 GPS 위치 정보를 EXIF에서 읽기 (3단계 폴백)
     *
     * 1단계: setRequireOriginal(uri) → 원본 EXIF 접근 (Android 10+ GPS 정밀 접근)
     * 2단계: 일반 content URI → 기기 촬영 사진은 GPS 포함 가능
     * 3단계: 파일 경로 직접 접근 → DATA 컬럼의 실제 파일 경로로 EXIF 읽기
     *
     * @param uri content:// URI
     * @param filePath MediaStore DATA 컬럼의 파일 경로 (nullable)
     */
    private fun getPhotoGeoInfo(uri: Uri, filePath: String? = null): PhotoGeoInfo? {
        // 1단계: setRequireOriginal (Android Q+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            try {
                val originalUri = MediaStore.setRequireOriginal(uri)
                val result = readExifGps(originalUri)
                if (result != null) return result
            } catch (e: SecurityException) {
                Log.d(TAG, "setRequireOriginal 권한 없음: ${uri.lastPathSegment}")
            } catch (e: Exception) {
                Log.d(TAG, "setRequireOriginal 실패: ${uri.lastPathSegment} - ${e.message}")
            }
        }

        // 2단계: 일반 content URI로 EXIF 읽기
        try {
            val result = readExifGps(uri)
            if (result != null) return result
        } catch (e: Exception) {
            Log.d(TAG, "일반 URI EXIF 실패: ${uri.lastPathSegment} - ${e.message}")
        }

        // 3단계: 파일 경로로 직접 EXIF 읽기 (폴백)
        if (!filePath.isNullOrBlank()) {
            try {
                val file = java.io.File(filePath)
                if (file.exists() && file.canRead()) {
                    val exif = ExifInterface(filePath)
                    val latLng = FloatArray(2)
                    if (exif.getLatLong(latLng)) {
                        val altitude = exif.getAltitude(0.0)
                        return PhotoGeoInfo(
                            latitude = latLng[0].toDouble(),
                            longitude = latLng[1].toDouble(),
                            altitude = altitude
                        )
                    }
                }
            } catch (e: Exception) {
                Log.d(TAG, "파일 경로 EXIF 실패: $filePath - ${e.message}")
            }
        }

        return null
    }

    /**
     * 파일 경로에서 상위 폴더명 추출
     * 예: /storage/emulated/0/DCIM/Camera/IMG.jpg → DCIM/Camera
     */
    private fun extractFolder(filePath: String?): String {
        if (filePath.isNullOrBlank()) return "알 수 없음"
        val file = java.io.File(filePath)
        val parent = file.parentFile ?: return "알 수 없음"
        // /storage/emulated/0/ 이후 경로만 추출
        val fullPath = parent.absolutePath
        val storagePrefix = "/storage/emulated/0/"
        return if (fullPath.startsWith(storagePrefix)) {
            fullPath.removePrefix(storagePrefix)
        } else {
            parent.name
        }
    }

    /**
     * URI에서 InputStream 열어 EXIF GPS 읽기
     */
    private fun readExifGps(uri: Uri): PhotoGeoInfo? {
        return context.contentResolver.openInputStream(uri)?.use { inputStream ->
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
    }
}
