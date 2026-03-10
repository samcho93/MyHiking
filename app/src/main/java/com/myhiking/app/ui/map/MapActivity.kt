package com.myhiking.app.ui.map

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.myhiking.app.R
import com.myhiking.app.data.model.DevicePhoto
import com.myhiking.app.data.model.MapProvider
import com.myhiking.app.data.model.Mountain
import com.myhiking.app.data.model.MountainWithPhotos
import com.myhiking.app.databinding.ActivityMapBinding
import com.myhiking.app.ui.photolist.PhotoListFragment
import com.myhiking.app.ui.reassign.ReassignFragment
import com.myhiking.app.util.PhotoPickerHelper

class MapActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMapBinding
    private val viewModel: MapViewModel by viewModels()

    private var currentMapManager: MapManagerInterface? = null
    private var naverMapManager: NaverMapManager? = null
    private var googleMapManager: GoogleMapManager? = null
    private var isLocationViewMode = false

    // 사진 추가 대상 산 정보 (photo picker 결과 처리용)
    private var pendingAddPhotoMountainId: Int? = null
    private var pendingAddPhotoMountainName: String? = null

    /**
     * 갤러리에서 사진 선택 결과 처리
     */
    private val photoPickerLauncher = registerForActivityResult(
        ActivityResultContracts.GetMultipleContents()
    ) { uris ->
        val mountainId = pendingAddPhotoMountainId
        val mountainName = pendingAddPhotoMountainName
        pendingAddPhotoMountainId = null
        pendingAddPhotoMountainName = null

        if (uris.isNullOrEmpty() || mountainId == null) return@registerForActivityResult

        // URI → MediaStore ID 변환
        val photoIds = uris.mapNotNull { uri ->
            PhotoPickerHelper.getMediaStoreId(this, uri)
        }

        if (photoIds.isEmpty()) {
            Toast.makeText(this, getString(R.string.no_photos_selected), Toast.LENGTH_SHORT).show()
            return@registerForActivityResult
        }

        viewModel.addPhotosToMountain(photoIds, mountainId)
        Toast.makeText(
            this,
            getString(R.string.add_photo_done, photoIds.size, mountainName ?: ""),
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
        setupClickListeners()

        if (savedInstanceState == null) {
            initializeMap(MapProvider.NAVER)
            viewModel.loadPhotosAndMatch()
        }
    }

    private fun setupObservers() {
        viewModel.isLoading.observe(this) { loading ->
            binding.mapProgressBar.visibility = if (loading) View.VISIBLE else View.GONE
        }

        viewModel.mountainsWithPhotos.observe(this) { mountains ->
            if (!isLocationViewMode) {
                currentMapManager?.setMountainMarkers(mountains)
            }
        }

        viewModel.currentProvider.observe(this) { provider ->
            binding.tvMapProvider.text = when (provider) {
                MapProvider.NAVER -> getString(R.string.map_provider_naver)
                MapProvider.GOOGLE -> getString(R.string.map_provider_google)
            }
            binding.fabSwitchMap.contentDescription = when (provider) {
                MapProvider.NAVER -> getString(R.string.switch_to_google)
                MapProvider.GOOGLE -> getString(R.string.switch_to_naver)
            }
        }

        viewModel.selectedMountain.observe(this) { mountain ->
            if (mountain != null && mountain.hasPhotos) {
                showPhotoList(mountain)
            }
        }

        viewModel.scanStatus.observe(this) { status ->
            if (status != null) {
                binding.tvScanStatus.text = status
                binding.tvScanStatus.visibility = View.VISIBLE
            } else {
                binding.tvScanStatus.visibility = View.GONE
            }
        }

        viewModel.rescanResult.observe(this) { count ->
            if (count != null) {
                val msg = if (count > 0) {
                    getString(R.string.rescan_complete, count)
                } else {
                    getString(R.string.rescan_no_new)
                }
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
                viewModel.clearRescanResult()
            }
        }
    }

    private fun setupClickListeners() {
        binding.fabSwitchMap.setOnClickListener {
            val newProvider = when (viewModel.currentProvider.value) {
                MapProvider.NAVER -> MapProvider.GOOGLE
                else -> MapProvider.NAVER
            }
            switchMap(newProvider)
            viewModel.switchMapProvider()
        }

        binding.fabFolderSelect.setOnClickListener {
            showFolderSelectDialog()
        }

        binding.fabRescan.setOnClickListener {
            Toast.makeText(this, getString(R.string.rescan_started), Toast.LENGTH_SHORT).show()
            viewModel.rescanPhotos()
        }

        binding.cbLocationView.setOnCheckedChangeListener { _, isChecked ->
            isLocationViewMode = isChecked
            if (isChecked) {
                // 위치로 보기: 모든 사진을 실제 GPS 좌표에 표시
                val allPhotos = viewModel.mountainsWithPhotos.value
                    ?.flatMap { it.photos } ?: emptyList()
                currentMapManager?.setPhotoLocationMarkers(allPhotos)
            } else {
                // 원래 보기: 산별 그룹 마커
                viewModel.mountainsWithPhotos.value?.let {
                    currentMapManager?.setMountainMarkers(it)
                }
            }
        }
    }

    private fun initializeMap(provider: MapProvider) {
        when (provider) {
            MapProvider.NAVER -> {
                naverMapManager = NaverMapManager(this)
                currentMapManager = naverMapManager
                naverMapManager?.initialize(
                    binding.mapContainer,
                    supportFragmentManager,
                    object : MapManagerInterface.OnMapReadyCallback {
                        override fun onMapReady() {
                            setupMarkerClickListener()
                            viewModel.mountainsWithPhotos.value?.let {
                                currentMapManager?.setMountainMarkers(it)
                            }
                        }
                    }
                )
            }
            MapProvider.GOOGLE -> {
                googleMapManager = GoogleMapManager(this)
                currentMapManager = googleMapManager
                googleMapManager?.initialize(
                    binding.mapContainer,
                    supportFragmentManager,
                    object : MapManagerInterface.OnMapReadyCallback {
                        override fun onMapReady() {
                            setupMarkerClickListener()
                            viewModel.mountainsWithPhotos.value?.let { mountains ->
                                currentMapManager?.setMountainMarkers(mountains)
                                val allPhotos = mountains.flatMap { it.photos }
                                if (allPhotos.isNotEmpty()) {
                                    currentMapManager?.fitBoundsToLocations(allPhotos)
                                }
                            }
                        }
                    }
                )
            }
        }
    }

    private fun switchMap(newProvider: MapProvider) {
        currentMapManager?.destroy(supportFragmentManager)
        currentMapManager = null
        initializeMap(newProvider)
    }

    private fun setupMarkerClickListener() {
        currentMapManager?.setOnMarkerClickListener { mountain ->
            viewModel.selectMountain(mountain)
        }
        currentMapManager?.setOnMarkerLongClickListener { mountain ->
            showMarkerLongPressDialog(mountain)
        }
        currentMapManager?.setOnEmptyMapLongClickListener { lat, lng ->
            showEmptyMapLongPressDialog(lat, lng)
        }
        currentMapManager?.setOnMarkerDragListener(object : MapManagerInterface.OnMarkerDragResultListener {
            override fun onDragStart(mountain: MountainWithPhotos) {
                Toast.makeText(
                    this@MapActivity,
                    getString(R.string.drag_hint, mountain.mountain.name),
                    Toast.LENGTH_SHORT
                ).show()
            }
            override fun onDragEnd(mountain: MountainWithPhotos, dropLat: Double, dropLng: Double) {
                handleMarkerDrop(mountain, dropLat, dropLng)
            }
        })
    }

    /**
     * 마커 길게 누르기 → 선택 다이얼로그 (전체 이동 / 전체 제외 / 사진 추가)
     */
    private fun showMarkerLongPressDialog(mountain: MountainWithPhotos) {
        val items = arrayOf(
            getString(R.string.action_move_all_photos),
            getString(R.string.action_exclude_all_photos),
            getString(R.string.action_add_photo)
        )

        AlertDialog.Builder(this)
            .setTitle(mountain.mountain.name)
            .setItems(items) { _, which ->
                when (which) {
                    0 -> showReassignSheet(mountain)
                    1 -> confirmExcludeAllPhotos(mountain)
                    2 -> launchPhotoPicker(mountain.mountain.id, mountain.mountain.name)
                }
            }
            .show()
    }

    /**
     * 전체 사진 제외 확인 다이얼로그
     */
    private fun confirmExcludeAllPhotos(mountain: MountainWithPhotos) {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.action_exclude_all_photos))
            .setMessage(getString(R.string.exclude_all_confirm, mountain.mountain.name, mountain.photoCount))
            .setPositiveButton(android.R.string.ok) { _, _ ->
                val photoIds = mountain.photos.map { it.id }
                viewModel.excludePhotos(photoIds)
                Toast.makeText(
                    this,
                    getString(R.string.exclude_all_done, photoIds.size),
                    Toast.LENGTH_SHORT
                ).show()
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }

    private fun showReassignSheet(mountain: MountainWithPhotos) {
        val nearby = viewModel.findNearbyMountains(
            mountain.mountain.latitude,
            mountain.mountain.longitude
        )
        val fragment = ReassignFragment.newInstance(mountain, nearby) { source, targetId ->
            viewModel.reassignAllPhotos(source, targetId)
        }
        fragment.show(supportFragmentManager, ReassignFragment.TAG)
    }

    private fun showPhotoList(mountain: MountainWithPhotos) {
        val fragment = PhotoListFragment.newInstance(
            mountain,
            onPhotoReassign = { photo ->
                showPhotoReassign(photo, mountain.mountain.name)
            },
            onPhotoExclude = { photo ->
                viewModel.excludePhoto(photo.id)
            },
            onAddPhoto = {
                launchPhotoPicker(mountain.mountain.id, mountain.mountain.name)
            }
        )
        fragment.show(supportFragmentManager, PhotoListFragment.TAG)
        viewModel.selectMountain(null)
    }

    /**
     * 개별 사진을 다른 산으로 이동하는 바텀시트 표시
     */
    private fun showPhotoReassign(photo: DevicePhoto, currentMountainName: String) {
        val nearby = viewModel.findNearbyMountains(photo.latitude, photo.longitude)
        val fragment = ReassignFragment.newInstanceForPhoto(
            photo, currentMountainName, nearby
        ) { photoId, targetId ->
            viewModel.reassignSinglePhoto(photoId, targetId)
        }
        fragment.show(supportFragmentManager, ReassignFragment.TAG)
    }

    /**
     * 빈 지도 영역 길게 누르기 → 근처 산 DB에서 검색 → 산 선택 → 사진 추가
     * 마커가 없는 산봉우리/산 위치에서도 사진을 추가할 수 있음
     */
    private fun showEmptyMapLongPressDialog(lat: Double, lng: Double) {
        // 5km 반경으로 근처 산 검색
        val nearbyMountains = viewModel.findNearbyMountains(lat, lng)

        if (nearbyMountains.isEmpty()) {
            Toast.makeText(this, getString(R.string.no_nearby_mountains), Toast.LENGTH_SHORT).show()
            return
        }

        // 산 이름과 거리 정보를 표시하는 목록
        val mountainNames = nearbyMountains.map { (mountain, distance) ->
            val distStr = if (distance < 1000) {
                "${distance.toInt()}m"
            } else {
                String.format("%.1fkm", distance / 1000)
            }
            "${mountain.name} (${mountain.altitude}m) - $distStr"
        }.toTypedArray()

        AlertDialog.Builder(this)
            .setTitle(getString(R.string.select_mountain_for_photo))
            .setItems(mountainNames) { _, which ->
                val (mountain, _) = nearbyMountains[which]
                launchPhotoPicker(mountain.id, mountain.name)
            }
            .show()
    }

    /**
     * 마커 드래그 드롭 처리: 드롭 위치 근처의 산을 찾아 재배치
     */
    private fun handleMarkerDrop(
        source: MountainWithPhotos,
        dropLat: Double,
        dropLng: Double
    ) {
        val nearbyMountains = viewModel.findNearbyMountains(dropLat, dropLng)
        val candidates = nearbyMountains.filter { it.first.id != source.mountain.id }

        if (candidates.isEmpty()) {
            currentMapManager?.snapMarkerBack(source)
            Toast.makeText(this, getString(R.string.drag_no_target), Toast.LENGTH_SHORT).show()
            return
        }

        if (candidates.size == 1) {
            val (target, distance) = candidates.first()
            val distStr = if (distance < 1000) "${distance.toInt()}m"
            else String.format("%.1fkm", distance / 1000)

            AlertDialog.Builder(this)
                .setTitle(getString(R.string.drag_confirm_title))
                .setMessage(
                    getString(R.string.drag_confirm_message, source.photoCount, source.mountain.name, target.name, distStr)
                )
                .setPositiveButton(android.R.string.ok) { _, _ ->
                    viewModel.reassignAllPhotos(source, target.id)
                    Toast.makeText(
                        this,
                        getString(R.string.reassign_bulk_done, source.photoCount, target.name),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .setNegativeButton(android.R.string.cancel) { _, _ ->
                    currentMapManager?.snapMarkerBack(source)
                }
                .setOnCancelListener {
                    currentMapManager?.snapMarkerBack(source)
                }
                .show()
        } else {
            // 여러 후보: ReassignFragment 바텀시트
            val fragment = ReassignFragment.newInstance(source, candidates) { src, targetId ->
                viewModel.reassignAllPhotos(src, targetId)
            }
            fragment.setOnDismissWithoutSelectionListener {
                currentMapManager?.snapMarkerBack(source)
            }
            fragment.show(supportFragmentManager, ReassignFragment.TAG)
        }
    }

    /**
     * 스캔 폴더 선택 다이얼로그 표시
     */
    private fun showFolderSelectDialog() {
        viewModel.loadPhotoFolders { allFolders ->
            if (allFolders.isEmpty()) {
                Toast.makeText(this, getString(R.string.no_photos_found), Toast.LENGTH_SHORT).show()
                return@loadPhotoFolders
            }

            viewModel.loadScanFolders { savedFolders ->
                val folderNames = allFolders.map { it.first }.toTypedArray()
                val folderLabels = allFolders.map { (name, count) ->
                    "$name ($count)"
                }.toTypedArray()

                val checked = BooleanArray(folderNames.size) { i ->
                    savedFolders == null || folderNames[i] in savedFolders
                }

                AlertDialog.Builder(this)
                    .setTitle(getString(R.string.folder_select_title))
                    .setMultiChoiceItems(folderLabels, checked) { _, which, isChecked ->
                        checked[which] = isChecked
                    }
                    .setPositiveButton(android.R.string.ok) { _, _ ->
                        val selectedFolders = folderNames.filterIndexed { i, _ -> checked[i] }.toSet()
                        if (selectedFolders.size == folderNames.size || selectedFolders.isEmpty()) {
                            // 전체 선택 또는 빈 선택 = 전체 스캔
                            viewModel.saveScanFoldersAndRescan(emptySet())
                            Toast.makeText(this, getString(R.string.folder_all_applied), Toast.LENGTH_SHORT).show()
                        } else {
                            viewModel.saveScanFoldersAndRescan(selectedFolders)
                            Toast.makeText(this, getString(R.string.folder_applied, selectedFolders.size), Toast.LENGTH_SHORT).show()
                        }
                    }
                    .setNegativeButton(android.R.string.cancel, null)
                    .setNeutralButton(getString(R.string.folder_select_all)) { dialog, _ ->
                        // 전체 선택 → 전체 스캔으로 변경
                        viewModel.saveScanFoldersAndRescan(emptySet())
                        Toast.makeText(this, getString(R.string.folder_all_applied), Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                    }
                    .show()
            }
        }
    }

    /**
     * 갤러리에서 사진 선택 (위치정보 없는 사진을 산에 추가)
     */
    private fun launchPhotoPicker(mountainId: Int, mountainName: String) {
        pendingAddPhotoMountainId = mountainId
        pendingAddPhotoMountainName = mountainName
        photoPickerLauncher.launch("image/*")
    }
}
