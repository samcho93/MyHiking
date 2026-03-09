package com.myhiking.app.data.cache

import android.content.Context
import android.net.Uri
import com.myhiking.app.data.model.DevicePhoto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.File

class MatchingCache(private val context: Context) {

    companion object {
        private const val CACHE_FILE = "matching_cache.json"
        private const val KEY_CACHED_PHOTOS = "cachedPhotos"
        private const val KEY_ASSIGNMENTS = "assignments"
        private const val KEY_USER_OVERRIDES = "userOverrides"
        private const val KEY_EXCLUDED_PHOTOS = "excludedPhotos"
        private const val KEY_LAST_SCAN = "lastScanTimestamp"
    }

    private fun getCacheFile(): File = File(context.filesDir, CACHE_FILE)

    private suspend fun readCacheJson(): JSONObject = withContext(Dispatchers.IO) {
        val file = getCacheFile()
        if (file.exists()) {
            try {
                JSONObject(file.readText())
            } catch (_: Exception) {
                JSONObject()
            }
        } else {
            JSONObject()
        }
    }

    private suspend fun writeCacheJson(json: JSONObject) = withContext(Dispatchers.IO) {
        getCacheFile().writeText(json.toString())
    }

    /**
     * 캐시된 사진 메타데이터 로드 (EXIF 재파싱 불필요)
     */
    suspend fun loadCachedPhotos(): List<DevicePhoto> = withContext(Dispatchers.IO) {
        val json = readCacheJson()
        val photosArray = json.optJSONArray(KEY_CACHED_PHOTOS) ?: return@withContext emptyList()
        val photos = mutableListOf<DevicePhoto>()

        for (i in 0 until photosArray.length()) {
            try {
                val obj = photosArray.getJSONObject(i)
                photos.add(
                    DevicePhoto(
                        id = obj.getLong("id"),
                        uri = Uri.parse(obj.getString("uri")),
                        latitude = obj.getDouble("lat"),
                        longitude = obj.getDouble("lng"),
                        altitude = obj.getDouble("alt"),
                        dateTaken = obj.getLong("date"),
                        displayName = obj.getString("name"),
                        size = obj.getLong("size")
                    )
                )
            } catch (_: Exception) {
                // Skip corrupt entries
            }
        }
        photos
    }

    /**
     * 스캔된 사진 메타데이터 캐시 저장
     */
    suspend fun saveCachedPhotos(photos: List<DevicePhoto>) = withContext(Dispatchers.IO) {
        val json = readCacheJson()
        val photosArray = JSONArray()

        for (photo in photos) {
            photosArray.put(JSONObject().apply {
                put("id", photo.id)
                put("uri", photo.uri.toString())
                put("lat", photo.latitude)
                put("lng", photo.longitude)
                put("alt", photo.altitude)
                put("date", photo.dateTaken)
                put("name", photo.displayName)
                put("size", photo.size)
            })
        }

        json.put(KEY_CACHED_PHOTOS, photosArray)
        json.put(KEY_LAST_SCAN, System.currentTimeMillis())
        writeCacheJson(json)
    }

    /**
     * 캐시된 사진 ID 목록 반환 (증분 스캔용)
     */
    suspend fun getCachedPhotoIds(): Set<Long> = withContext(Dispatchers.IO) {
        val json = readCacheJson()
        val photosArray = json.optJSONArray(KEY_CACHED_PHOTOS) ?: return@withContext emptySet()
        val ids = mutableSetOf<Long>()
        for (i in 0 until photosArray.length()) {
            try {
                ids.add(photosArray.getJSONObject(i).getLong("id"))
            } catch (_: Exception) {}
        }
        ids
    }

    /**
     * 자동 매칭 결과 저장 (photoId → mountainId)
     */
    suspend fun saveAssignments(assignments: Map<Long, Int>) = withContext(Dispatchers.IO) {
        val json = readCacheJson()
        val assignObj = JSONObject()
        for ((photoId, mountainId) in assignments) {
            assignObj.put(photoId.toString(), mountainId)
        }
        json.put(KEY_ASSIGNMENTS, assignObj)
        writeCacheJson(json)
    }

    /**
     * 자동 매칭 결과 로드
     */
    suspend fun loadAssignments(): Map<Long, Int> = withContext(Dispatchers.IO) {
        val json = readCacheJson()
        val assignObj = json.optJSONObject(KEY_ASSIGNMENTS) ?: return@withContext emptyMap()
        val result = mutableMapOf<Long, Int>()
        val keys = assignObj.keys()
        while (keys.hasNext()) {
            val key = keys.next()
            try {
                result[key.toLong()] = assignObj.getInt(key)
            } catch (_: Exception) {}
        }
        result
    }

    /**
     * 사용자 수동 재배치 저장
     */
    suspend fun saveUserOverride(photoId: Long, mountainId: Int) = withContext(Dispatchers.IO) {
        val json = readCacheJson()
        val overrides = json.optJSONObject(KEY_USER_OVERRIDES) ?: JSONObject()
        overrides.put(photoId.toString(), mountainId)
        json.put(KEY_USER_OVERRIDES, overrides)
        writeCacheJson(json)
    }

    /**
     * 사용자 수동 재배치 목록 로드
     */
    suspend fun getUserOverrides(): Map<Long, Int> = withContext(Dispatchers.IO) {
        val json = readCacheJson()
        val overrides = json.optJSONObject(KEY_USER_OVERRIDES) ?: return@withContext emptyMap()
        val result = mutableMapOf<Long, Int>()
        val keys = overrides.keys()
        while (keys.hasNext()) {
            val key = keys.next()
            try {
                result[key.toLong()] = overrides.getInt(key)
            } catch (_: Exception) {}
        }
        result
    }

    /**
     * 사용자 수동 재배치 삭제 (원래 매칭으로 복원)
     */
    suspend fun removeUserOverride(photoId: Long) = withContext(Dispatchers.IO) {
        val json = readCacheJson()
        val overrides = json.optJSONObject(KEY_USER_OVERRIDES) ?: return@withContext
        overrides.remove(photoId.toString())
        json.put(KEY_USER_OVERRIDES, overrides)
        writeCacheJson(json)
    }

    /**
     * 사진 제외 (목록에서 숨기기, 실제 삭제 아님)
     */
    suspend fun excludePhoto(photoId: Long) = withContext(Dispatchers.IO) {
        val json = readCacheJson()
        val excluded = json.optJSONArray(KEY_EXCLUDED_PHOTOS) ?: org.json.JSONArray()
        // 중복 방지
        val existing = (0 until excluded.length()).map { excluded.getLong(it) }.toSet()
        if (photoId !in existing) {
            excluded.put(photoId)
        }
        json.put(KEY_EXCLUDED_PHOTOS, excluded)
        writeCacheJson(json)
    }

    /**
     * 제외된 사진 ID 목록 로드
     */
    suspend fun getExcludedPhotoIds(): Set<Long> = withContext(Dispatchers.IO) {
        val json = readCacheJson()
        val excluded = json.optJSONArray(KEY_EXCLUDED_PHOTOS) ?: return@withContext emptySet()
        val ids = mutableSetOf<Long>()
        for (i in 0 until excluded.length()) {
            try { ids.add(excluded.getLong(i)) } catch (_: Exception) {}
        }
        ids
    }

    /**
     * 제외된 사진 복원
     */
    suspend fun restoreExcludedPhoto(photoId: Long) = withContext(Dispatchers.IO) {
        val json = readCacheJson()
        val excluded = json.optJSONArray(KEY_EXCLUDED_PHOTOS) ?: return@withContext
        val newExcluded = org.json.JSONArray()
        for (i in 0 until excluded.length()) {
            val id = excluded.optLong(i, -1)
            if (id != photoId) newExcluded.put(id)
        }
        json.put(KEY_EXCLUDED_PHOTOS, newExcluded)
        writeCacheJson(json)
    }
}
