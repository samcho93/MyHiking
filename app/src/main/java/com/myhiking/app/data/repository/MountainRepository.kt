package com.myhiking.app.data.repository

import android.content.Context
import android.location.Geocoder
import com.myhiking.app.data.model.DevicePhoto
import com.myhiking.app.data.model.Mountain
import com.myhiking.app.data.model.MountainWithPhotos
import com.myhiking.app.data.source.KoreanMountainData
import com.myhiking.app.util.LocationUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Locale

class MountainRepository(private val context: Context) {

    companion object {
        const val DEFAULT_RADIUS_METERS = 1000.0
        // Pre-filter threshold in degrees (~0.01 deg ≈ 1.1km)
        private const val LAT_LNG_THRESHOLD = 0.011
        // Altitude comparison settings
        // 사진 고도가 산 고도의 최소 20% 이상이어야 산에서 찍은 것으로 판단
        private const val MIN_ALTITUDE_RATIO = 0.2
        // GPS 고도 오차 허용 범위 (산 정상 위로 허용하는 미터)
        private const val ALTITUDE_UPPER_TOLERANCE = 500.0
        // 최소 고도 기준 (이 이상이어야 산으로 판단, 미터)
        private const val MIN_ALTITUDE_THRESHOLD = 30.0
        // 고도 차이 가중치: 고도 차이 1m = 수평 거리 ALTITUDE_WEIGHT m 에 해당
        // 예: 가중치 3.0이면 고도 100m 차이 = 수평 거리 300m 페널티
        private const val ALTITUDE_WEIGHT = 3.0
        // 해외 사진: 산으로 판단하기 위한 최소 해발 고도 (미터)
        private const val FOREIGN_MIN_ALTITUDE = 100.0
        // 해외 사진 클러스터링 반경 (미터)
        private const val CLUSTER_RADIUS_METERS = 1000.0
        // 한국 영역 (한국 산 DB로 매칭 시도하는 범위)
        private const val KOREA_LAT_MIN = 33.0
        private const val KOREA_LAT_MAX = 38.6
        private const val KOREA_LNG_MIN = 124.5
        private const val KOREA_LNG_MAX = 131.9
    }

    fun getMountains(): List<Mountain> = KoreanMountainData.getMountains(context)

    /**
     * 사진의 EXIF 고도와 산의 고도를 비교하여 산에서 찍은 사진인지 판단
     *
     * @param photoAltitude 사진 EXIF에서 읽은 해발 고도 (미터)
     * @param mountainAltitude 산의 알려진 해발 고도 (미터)
     * @return 고도가 호환되면 true
     */
    private fun isAltitudeCompatible(photoAltitude: Double, mountainAltitude: Int): Boolean {
        // 사진에 고도 데이터가 없으면 (0 이하) 고도 비교 생략 → 거리만으로 판단
        if (photoAltitude <= 0.0) return true

        // 산의 고도 데이터가 없으면 비교 생략
        if (mountainAltitude <= 0) return true

        val mountainAlt = mountainAltitude.toDouble()

        // 사진 고도가 최소 기준(50m) 이하이면 평지로 판단 → 산 사진이 아님
        if (photoAltitude < MIN_ALTITUDE_THRESHOLD) return false

        // 사진 고도가 산 고도의 20% 이상이어야 함 (등산 초입/등산로 사진 허용)
        // 예: 1000m 산 → 최소 200m 이상에서 찍어야 함
        val minAltitude = mountainAlt * MIN_ALTITUDE_RATIO

        // 사진 고도가 산 정상보다 너무 높으면 안됨 (GPS 오차 500m 허용)
        val maxAltitude = mountainAlt + ALTITUDE_UPPER_TOLERANCE

        return photoAltitude >= minAltitude && photoAltitude <= maxAltitude
    }

    /**
     * 사진-산 매칭 점수 계산 (낮을수록 좋은 매칭)
     * 수평 거리 + 고도 차이(가중치 적용) 를 합산하여
     * 단순 거리가 아닌 종합 점수로 최적 산을 결정
     *
     * 예: 산A(거리 300m, 고도차 200m), 산B(거리 500m, 고도차 10m)
     *   → 산A 점수 = 300 + 200*3 = 900
     *   → 산B 점수 = 500 + 10*3  = 530  ← 산B가 더 적합
     */
    private fun calculateMatchScore(
        horizontalDistance: Double,
        photoAltitude: Double,
        mountainAltitude: Int
    ): Double {
        // 고도 데이터가 없으면 수평 거리만 사용
        if (photoAltitude <= 0.0 || mountainAltitude <= 0) {
            return horizontalDistance
        }
        val altitudeDiff = kotlin.math.abs(photoAltitude - mountainAltitude.toDouble())
        return horizontalDistance + (altitudeDiff * ALTITUDE_WEIGHT)
    }

    /**
     * 특정 좌표 근처의 산 목록을 거리순으로 반환 (재배치용)
     */
    fun findNearbyMountains(lat: Double, lng: Double, radiusMeters: Double = 3000.0): List<Pair<Mountain, Double>> {
        val mountains = getMountains()
        val nearby = mutableListOf<Pair<Mountain, Double>>()
        for (mountain in mountains) {
            val distance = LocationUtils.distanceInMeters(lat, lng, mountain.latitude, mountain.longitude)
            if (distance <= radiusMeters) {
                nearby.add(Pair(mountain, distance))
            }
        }
        return nearby.sortedBy { it.second }
    }

    suspend fun matchPhotosToMountains(
        photos: List<DevicePhoto>,
        userOverrides: Map<Long, Int> = emptyMap(),
        radiusMeters: Double = DEFAULT_RADIUS_METERS
    ): List<MountainWithPhotos> = withContext(Dispatchers.Default) {
        val mountains = getMountains()

        if (photos.isEmpty()) {
            return@withContext mountains.map { MountainWithPhotos(it, emptyList()) }
        }

        val assignedPhotos = mutableSetOf<Long>()
        val mountainPhotosMap = mutableMapOf<Int, MutableList<DevicePhoto>>()

        // 1. 사용자 오버라이드 우선 적용
        for ((photoId, mountainId) in userOverrides) {
            val photo = photos.find { it.id == photoId } ?: continue
            assignedPhotos.add(photoId)
            mountainPhotosMap.getOrPut(mountainId) { mutableListOf() }.add(photo)
        }

        data class PhotoMatch(
            val photo: DevicePhoto,
            val score: Double,
            val mountainId: Int
        )

        val allMatches = mutableListOf<PhotoMatch>()

        // 2. 오버라이드되지 않은 사진만 자동 매칭
        val autoMatchPhotos = photos.filter { it.id !in assignedPhotos }
        for (photo in autoMatchPhotos) {
            for (mountain in mountains) {
                // Quick lat/lng check before expensive distance calculation
                if (kotlin.math.abs(photo.latitude - mountain.latitude) > LAT_LNG_THRESHOLD ||
                    kotlin.math.abs(photo.longitude - mountain.longitude) > LAT_LNG_THRESHOLD
                ) continue

                val distance = LocationUtils.distanceInMeters(
                    photo.latitude, photo.longitude,
                    mountain.latitude, mountain.longitude
                )
                if (distance <= radiusMeters) {
                    // 거리 매칭 후 고도 비교로 산에서 찍은 사진인지 검증
                    if (isAltitudeCompatible(photo.altitude, mountain.altitude)) {
                        val score = calculateMatchScore(
                            distance, photo.altitude, mountain.altitude
                        )
                        allMatches.add(PhotoMatch(photo, score, mountain.id))
                    }
                }
            }
        }

        // 종합 점수순 정렬 → 거리+고도가 가장 잘 맞는 산부터 할당
        allMatches.sortBy { it.score }

        for (match in allMatches) {
            if (match.photo.id !in assignedPhotos) {
                assignedPhotos.add(match.photo.id)
                mountainPhotosMap.getOrPut(match.mountainId) { mutableListOf() }
                    .add(match.photo)
            }
        }

        // 한국 산 DB에 매칭된 결과
        val result = mutableListOf<MountainWithPhotos>()
        mountains.filter { mountainPhotosMap.containsKey(it.id) }.forEach { mountain ->
            result.add(
                MountainWithPhotos(
                    mountain = mountain,
                    photos = mountainPhotosMap[mountain.id] ?: emptyList()
                )
            )
        }

        // 매칭되지 않은 사진 중 해외 산 사진 처리
        val unmatchedPhotos = photos.filter { it.id !in assignedPhotos }
        val foreignMountainPhotos = unmatchedPhotos.filter { photo ->
            // 고도 100m 이상 + 한국 영역 밖 → 해외 산 사진으로 판단
            photo.altitude >= FOREIGN_MIN_ALTITUDE && !isInKorea(photo.latitude, photo.longitude)
        }

        if (foreignMountainPhotos.isNotEmpty()) {
            val clusters = clusterPhotos(foreignMountainPhotos)
            var virtualId = -1
            for (cluster in clusters) {
                val avgLat = cluster.map { it.latitude }.average()
                val avgLng = cluster.map { it.longitude }.average()
                val maxAlt = cluster.maxOf { it.altitude }

                // 역지오코딩으로 영문 이름 가져오기
                val locationName = getLocationName(avgLat, avgLng)
                val displayName = if (locationName != null) {
                    "$locationName (${maxAlt.toInt()}m)"
                } else {
                    "해발 ${maxAlt.toInt()}m"
                }
                val englishName = if (locationName != null) {
                    "$locationName (${maxAlt.toInt()}m)"
                } else {
                    "Elev. ${maxAlt.toInt()}m"
                }

                val virtualMountain = Mountain(
                    id = virtualId--,
                    name = displayName,
                    nameEn = englishName,
                    latitude = avgLat,
                    longitude = avgLng,
                    altitude = maxAlt.toInt(),
                    region = ""
                )
                result.add(MountainWithPhotos(virtualMountain, cluster))
            }
        }

        result
    }

    /**
     * 역지오코딩으로 해외 위치의 지역명 가져오기
     * featureName은 주소번호/POI명이 올라와서 부정확 → 지역명+국가명 사용
     */
    @Suppress("DEPRECATION")
    private suspend fun getLocationName(lat: Double, lng: Double): String? = withContext(Dispatchers.IO) {
        try {
            val geocoder = Geocoder(context, Locale.ENGLISH)
            val addresses = geocoder.getFromLocation(lat, lng, 1)
            if (!addresses.isNullOrEmpty()) {
                val address = addresses[0]
                val locality = address.locality ?: address.subAdminArea ?: address.adminArea
                val country = address.countryName
                when {
                    locality != null && country != null -> "$locality, $country"
                    locality != null -> locality
                    country != null -> country
                    else -> null
                }
            } else null
        } catch (_: Exception) {
            null
        }
    }

    /**
     * 한국 영역 내 좌표인지 확인
     */
    private fun isInKorea(lat: Double, lng: Double): Boolean {
        return lat in KOREA_LAT_MIN..KOREA_LAT_MAX &&
                lng in KOREA_LNG_MIN..KOREA_LNG_MAX
    }

    /**
     * 근접 사진들을 클러스터로 묶기 (1km 반경)
     * 가까운 사진끼리 하나의 산 지점으로 그룹화
     */
    private fun clusterPhotos(photos: List<DevicePhoto>): List<List<DevicePhoto>> {
        val clusters = mutableListOf<MutableList<DevicePhoto>>()
        val clusterCenters = mutableListOf<Pair<Double, Double>>() // lat, lng

        for (photo in photos) {
            var addedToCluster = false
            for (i in clusters.indices) {
                val (centerLat, centerLng) = clusterCenters[i]
                val distance = LocationUtils.distanceInMeters(
                    photo.latitude, photo.longitude, centerLat, centerLng
                )
                if (distance <= CLUSTER_RADIUS_METERS) {
                    clusters[i].add(photo)
                    // 클러스터 중심 업데이트 (평균)
                    val size = clusters[i].size
                    clusterCenters[i] = Pair(
                        (centerLat * (size - 1) + photo.latitude) / size,
                        (centerLng * (size - 1) + photo.longitude) / size
                    )
                    addedToCluster = true
                    break
                }
            }
            if (!addedToCluster) {
                clusters.add(mutableListOf(photo))
                clusterCenters.add(Pair(photo.latitude, photo.longitude))
            }
        }
        return clusters
    }
}
