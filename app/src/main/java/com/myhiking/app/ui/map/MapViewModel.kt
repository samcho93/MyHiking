package com.myhiking.app.ui.map

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.myhiking.app.data.cache.MatchingCache
import com.myhiking.app.data.model.DevicePhoto
import com.myhiking.app.data.model.MapProvider
import com.myhiking.app.data.model.Mountain
import com.myhiking.app.data.model.MountainWithPhotos
import com.myhiking.app.data.repository.MountainRepository
import com.myhiking.app.data.repository.PhotoRepository
import kotlinx.coroutines.launch

class MapViewModel(application: Application) : AndroidViewModel(application) {

    private val photoRepository = PhotoRepository(application)
    private val mountainRepository = MountainRepository(application)
    private val matchingCache = MatchingCache(application)

    private val _mountainsWithPhotos = MutableLiveData<List<MountainWithPhotos>>()
    val mountainsWithPhotos: LiveData<List<MountainWithPhotos>> = _mountainsWithPhotos

    private val _allPhotos = MutableLiveData<List<DevicePhoto>>()
    val allPhotos: LiveData<List<DevicePhoto>> = _allPhotos

    private val _currentProvider = MutableLiveData(MapProvider.NAVER)
    val currentProvider: LiveData<MapProvider> = _currentProvider

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _selectedMountain = MutableLiveData<MountainWithPhotos?>()
    val selectedMountain: LiveData<MountainWithPhotos?> = _selectedMountain

    private val _rescanResult = MutableLiveData<Int?>()
    val rescanResult: LiveData<Int?> = _rescanResult

    private val _scanStatus = MutableLiveData<String?>()
    val scanStatus: LiveData<String?> = _scanStatus

    fun loadPhotosAndMatch() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // 1. 캐시에서 기존 데이터 로드
                val cachedPhotos = matchingCache.loadCachedPhotos()
                val cachedPhotoIds = cachedPhotos.map { it.id }.toSet()
                val userOverrides = matchingCache.getUserOverrides()

                // 제외된 사진 ID 로드
                val excludedIds = matchingCache.getExcludedPhotoIds()

                // 2. 캐시가 있으면 즉시 표시
                if (cachedPhotos.isNotEmpty()) {
                    val filteredCached = cachedPhotos.filter { it.id !in excludedIds }
                    val quickMatch = mountainRepository.matchPhotosToMountains(
                        filteredCached, userOverrides
                    )
                    _mountainsWithPhotos.value = quickMatch
                    _allPhotos.value = cachedPhotos
                }

                // 3. 증분 스캔 (새 사진만 EXIF 파싱)
                val scanFolders = matchingCache.getScanFolders()
                val newPhotos = photoRepository.scanGeotaggedPhotos(
                    excludeIds = cachedPhotoIds,
                    allowedFolders = scanFolders,
                    onProgress = { folder, scanned, found ->
                        _scanStatus.postValue(
                            if (folder.isNotEmpty()) "$folder (${scanned}장 검사, ${found}장 발견)"
                            else null
                        )
                    }
                )

                // 4. 삭제된 사진 감지
                val currentMediaIds = photoRepository.getAllPhotoIds()
                val validCachedPhotos = cachedPhotos.filter { it.id in currentMediaIds }

                // 5. 캐시 + 새 사진 합치기
                val allPhotos = validCachedPhotos + newPhotos
                _allPhotos.value = allPhotos

                // 6. 전체 재매칭 (사용자 오버라이드 유지, 제외된 사진 제외)
                val filteredPhotos = allPhotos.filter { it.id !in excludedIds }
                val matched = mountainRepository.matchPhotosToMountains(
                    filteredPhotos, userOverrides
                )
                _mountainsWithPhotos.value = matched

                // 7. 캐시 저장
                matchingCache.saveCachedPhotos(allPhotos)

                // 8. 자동 매칭 결과 저장
                val assignments = mutableMapOf<Long, Int>()
                for (mtn in matched) {
                    for (photo in mtn.photos) {
                        assignments[photo.id] = mtn.mountain.id
                    }
                }
                matchingCache.saveAssignments(assignments)
            } catch (_: Exception) {
                _mountainsWithPhotos.value = emptyList()
                _allPhotos.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }

    /**
     * 마커 전체(모든 사진)를 다른 산으로 일괄 이동
     */
    fun reassignAllPhotos(source: MountainWithPhotos, targetMountainId: Int) {
        viewModelScope.launch {
            // 모든 사진을 타겟 산으로 오버라이드 저장
            for (photo in source.photos) {
                matchingCache.saveUserOverride(photo.id, targetMountainId)
            }

            val allPhotos = _allPhotos.value ?: return@launch
            val userOverrides = matchingCache.getUserOverrides()
            val matched = mountainRepository.matchPhotosToMountains(
                allPhotos, userOverrides
            )
            _mountainsWithPhotos.value = matched

            val assignments = mutableMapOf<Long, Int>()
            for (mtn in matched) {
                for (p in mtn.photos) {
                    assignments[p.id] = mtn.mountain.id
                }
            }
            matchingCache.saveAssignments(assignments)
        }
    }

    /**
     * 개별 사진 한 장을 다른 산으로 이동
     */
    fun reassignSinglePhoto(photoId: Long, targetMountainId: Int) {
        viewModelScope.launch {
            matchingCache.saveUserOverride(photoId, targetMountainId)

            val allPhotos = _allPhotos.value ?: return@launch
            val userOverrides = matchingCache.getUserOverrides()
            val matched = mountainRepository.matchPhotosToMountains(
                allPhotos, userOverrides
            )
            _mountainsWithPhotos.value = matched

            val assignments = mutableMapOf<Long, Int>()
            for (mtn in matched) {
                for (p in mtn.photos) {
                    assignments[p.id] = mtn.mountain.id
                }
            }
            matchingCache.saveAssignments(assignments)
        }
    }

    /**
     * 사진을 목록에서 제외 (실제 삭제 아님, 썸네일만 숨김)
     */
    fun excludePhoto(photoId: Long) {
        viewModelScope.launch {
            matchingCache.excludePhoto(photoId)
            rematchAll()
        }
    }

    /**
     * 여러 사진을 한꺼번에 제외
     */
    fun excludePhotos(photoIds: List<Long>) {
        viewModelScope.launch {
            for (id in photoIds) {
                matchingCache.excludePhoto(id)
            }
            rematchAll()
        }
    }

    /**
     * 위치정보 없는 사진을 특정 산에 추가 (사용자 오버라이드로 저장)
     * @param photoIds 추가할 사진 ID 목록
     * @param mountainId 대상 산 ID
     */
    fun addPhotosToMountain(photoIds: List<Long>, mountainId: Int) {
        viewModelScope.launch {
            // 각 사진을 해당 산에 오버라이드로 지정
            for (id in photoIds) {
                matchingCache.saveUserOverride(id, mountainId)
            }

            // 사진이 allPhotos에 없을 수 있으므로 (위치정보 없는 사진) 캐시에도 추가
            val currentAll = _allPhotos.value?.toMutableList() ?: mutableListOf()
            val existingIds = currentAll.map { it.id }.toSet()
            val newPhotos = photoIds.filter { it !in existingIds }

            if (newPhotos.isNotEmpty()) {
                // 위치정보 없는 사진을 scanNoGpsPhotos로 가져와서 추가
                val noGpsPhotos = photoRepository.scanPhotosByIds(newPhotos.toSet())
                currentAll.addAll(noGpsPhotos)
                _allPhotos.value = currentAll
                matchingCache.saveCachedPhotos(currentAll)
            }

            rematchAll()
        }
    }

    private suspend fun rematchAll() {
        val allPhotos = _allPhotos.value ?: return
        val userOverrides = matchingCache.getUserOverrides()
        val excludedIds = matchingCache.getExcludedPhotoIds()
        val filteredPhotos = allPhotos.filter { it.id !in excludedIds }
        val matched = mountainRepository.matchPhotosToMountains(filteredPhotos, userOverrides)
        _mountainsWithPhotos.value = matched

        val assignments = mutableMapOf<Long, Int>()
        for (mtn in matched) {
            for (p in mtn.photos) {
                assignments[p.id] = mtn.mountain.id
            }
        }
        matchingCache.saveAssignments(assignments)
    }

    /**
     * 새 사진 다시 스캔: 이전 캐시 이후 추가된 사진만 증분 스캔하여 추가
     */
    fun rescanPhotos() {
        viewModelScope.launch {
            _isLoading.value = true
            _rescanResult.value = null
            try {
                val cachedPhotos = matchingCache.loadCachedPhotos()
                val cachedPhotoIds = cachedPhotos.map { it.id }.toSet()
                val excludedIds = matchingCache.getExcludedPhotoIds()

                // 증분 스캔 (새 사진만)
                val scanFolders = matchingCache.getScanFolders()
                val newPhotos = photoRepository.scanGeotaggedPhotos(
                    excludeIds = cachedPhotoIds,
                    allowedFolders = scanFolders,
                    onProgress = { folder, scanned, found ->
                        _scanStatus.postValue(
                            if (folder.isNotEmpty()) "$folder (${scanned}장 검사, ${found}장 발견)"
                            else null
                        )
                    }
                )

                // 삭제된 사진 감지
                val currentMediaIds = photoRepository.getAllPhotoIds()
                val validCachedPhotos = cachedPhotos.filter { it.id in currentMediaIds }

                // 합치기
                val allPhotos = validCachedPhotos + newPhotos
                _allPhotos.value = allPhotos

                // 재매칭
                val userOverrides = matchingCache.getUserOverrides()
                val filteredPhotos = allPhotos.filter { it.id !in excludedIds }
                val matched = mountainRepository.matchPhotosToMountains(
                    filteredPhotos, userOverrides
                )
                _mountainsWithPhotos.value = matched

                // 캐시 저장
                matchingCache.saveCachedPhotos(allPhotos)
                val assignments = mutableMapOf<Long, Int>()
                for (mtn in matched) {
                    for (p in mtn.photos) {
                        assignments[p.id] = mtn.mountain.id
                    }
                }
                matchingCache.saveAssignments(assignments)

                _rescanResult.value = newPhotos.size
            } catch (_: Exception) {
                _rescanResult.value = 0
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun clearRescanResult() {
        _rescanResult.value = null
    }

    /**
     * 기기의 모든 사진 폴더 목록 조회 (폴더명, 사진 수)
     */
    fun loadPhotoFolders(callback: (List<Pair<String, Int>>) -> Unit) {
        viewModelScope.launch {
            val folders = photoRepository.getPhotoFolders()
            callback(folders)
        }
    }

    /**
     * 현재 저장된 스캔 폴더 설정 로드
     */
    fun loadScanFolders(callback: (Set<String>?) -> Unit) {
        viewModelScope.launch {
            val folders = matchingCache.getScanFolders()
            callback(folders)
        }
    }

    /**
     * 스캔 대상 폴더 저장 후 캐시 초기화 및 전체 재스캔
     */
    fun saveScanFoldersAndRescan(folders: Set<String>) {
        viewModelScope.launch {
            matchingCache.saveScanFolders(folders)
            // 캐시 초기화 후 전체 재스캔
            matchingCache.saveCachedPhotos(emptyList())
            loadPhotosAndMatch()
        }
    }

    fun findNearbyMountains(lat: Double, lng: Double): List<Pair<Mountain, Double>> {
        return mountainRepository.findNearbyMountains(lat, lng)
    }

    fun switchMapProvider() {
        _currentProvider.value = when (_currentProvider.value) {
            MapProvider.NAVER -> MapProvider.GOOGLE
            MapProvider.GOOGLE -> MapProvider.NAVER
            else -> MapProvider.NAVER
        }
    }

    fun selectMountain(mountain: MountainWithPhotos?) {
        _selectedMountain.value = mountain
    }
}
