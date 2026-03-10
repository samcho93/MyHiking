package com.myhiking.app.ui.map

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.PointF
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.myhiking.app.R
import com.myhiking.app.data.model.DevicePhoto
import com.myhiking.app.data.model.MountainWithPhotos
import com.myhiking.app.data.source.Famous100Mountains
import com.myhiking.app.util.BitmapUtils
import com.naver.maps.geometry.LatLng
import com.naver.maps.geometry.LatLngBounds
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.NaverMapOptions
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NaverMapManager(private val context: Context) : MapManagerInterface {

    private var naverMap: NaverMap? = null
    private var mapFragment: MapFragment? = null
    private val markers = mutableListOf<Marker>()
    private var markerClickListener: ((MountainWithPhotos) -> Unit)? = null
    private var markerLongClickListener: ((MountainWithPhotos) -> Unit)? = null
    private var emptyMapLongClickListener: ((Double, Double) -> Unit)? = null
    private val markerMountainMap = mutableMapOf<Marker, MountainWithPhotos>()
    private var markerDragListener: MapManagerInterface.OnMarkerDragResultListener? = null
    private val markerOriginalPositions = mutableMapOf<Marker, LatLng>()

    // Custom drag state (네이버 SDK는 네이티브 마커 드래그 미지원)
    private var isDragMode = false
    private var dragMarker: Marker? = null
    private var dragMountain: MountainWithPhotos? = null

    companion object {
        private const val MAP_FRAGMENT_TAG = "naver_map"
        private val KOREA_CENTER = LatLng(36.5, 127.5)
        private const val DEFAULT_ZOOM = 7f
        private val KOREA_BOUNDS = LatLngBounds(
            LatLng(33.0, 124.5),
            LatLng(38.6, 131.9)
        )
        /** 마커 직접 터치 판정 (dp) — 이 범위 내 롱프레스 시 드래그 시작 */
        private const val DRAG_DIRECT_DP = 30f
        /** 마커 근처 터치 판정 (dp) — 이 범위 내 롱프레스 시 액션 메뉴 */
        private const val MARKER_NEAR_DP = 50f
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun initialize(
        container: ViewGroup,
        fragmentManager: FragmentManager,
        callback: MapManagerInterface.OnMapReadyCallback
    ) {
        val options = NaverMapOptions()
            .camera(com.naver.maps.map.CameraPosition(KOREA_CENTER, DEFAULT_ZOOM.toDouble()))

        mapFragment = MapFragment.newInstance(options)
        fragmentManager.beginTransaction()
            .replace(container.id, mapFragment!!, MAP_FRAGMENT_TAG)
            .commitAllowingStateLoss()

        mapFragment?.getMapAsync { map ->
            naverMap = map
            map.extent = KOREA_BOUNDS
            map.minZoom = 6.0
            map.uiSettings.isZoomControlEnabled = true
            map.uiSettings.isCompassEnabled = true

            // Long-press: 드래그 시작 / 액션 메뉴 / 빈 지도 메뉴
            map.setOnMapLongClickListener { _, latLng ->
                if (isDragMode) return@setOnMapLongClickListener

                val density = context.resources.displayMetrics.density
                val directPx = DRAG_DIRECT_DP * density
                val nearPx = MARKER_NEAR_DP * density

                val info = findNearestMarkerInfo(latLng, nearPx)
                if (info != null) {
                    if (info.distPx <= directPx && markerDragListener != null) {
                        // 마커 위 직접 롱프레스 → 드래그 시작
                        isDragMode = true
                        dragMarker = info.marker
                        dragMountain = info.mountain
                        map.uiSettings.isScrollGesturesEnabled = false
                        map.uiSettings.isZoomGesturesEnabled = false
                        markerDragListener?.onDragStart(info.mountain)
                    } else {
                        // 마커 근처 롱프레스 → 액션 메뉴
                        markerLongClickListener?.invoke(info.mountain)
                    }
                } else {
                    emptyMapLongClickListener?.invoke(latLng.latitude, latLng.longitude)
                }
            }

            // 터치 리스너: 드래그 모드에서 마커 위치 실시간 갱신
            mapFragment?.view?.setOnTouchListener { _, event ->
                if (!isDragMode) return@setOnTouchListener false
                when (event.action) {
                    MotionEvent.ACTION_MOVE -> {
                        val projection = naverMap?.projection
                            ?: return@setOnTouchListener true
                        val latLng = projection.fromScreenLocation(
                            PointF(event.x, event.y)
                        )
                        dragMarker?.position = latLng
                    }
                    MotionEvent.ACTION_UP -> {
                        val projection = naverMap?.projection
                        val marker = dragMarker
                        val mountain = dragMountain
                        if (projection != null && marker != null && mountain != null) {
                            val latLng = projection.fromScreenLocation(
                                PointF(event.x, event.y)
                            )
                            marker.position = latLng
                            markerDragListener?.onDragEnd(
                                mountain, latLng.latitude, latLng.longitude
                            )
                        }
                        endDragMode()
                    }
                    MotionEvent.ACTION_CANCEL -> {
                        dragMountain?.let { snapMarkerBack(it) }
                        endDragMode()
                    }
                }
                true // 드래그 모드 중 이벤트 소비
            }

            callback.onMapReady()
        }
    }

    /** 드래그 모드 종료 — 지도 제스처 복원 */
    private fun endDragMode() {
        isDragMode = false
        dragMarker = null
        dragMountain = null
        naverMap?.uiSettings?.isScrollGesturesEnabled = true
        naverMap?.uiSettings?.isZoomGesturesEnabled = true
    }

    override fun destroy(fragmentManager: FragmentManager) {
        endDragMode()
        clearMarkers()
        mapFragment?.let {
            fragmentManager.beginTransaction().remove(it).commitAllowingStateLoss()
        }
        mapFragment = null
        naverMap = null
    }

    override fun setMountainMarkers(mountains: List<MountainWithPhotos>) {
        clearMarkers()
        val map = naverMap ?: return

        val mountainsWithPhotos = mountains.filter { it.hasPhotos }

        for (mtn in mountainsWithPhotos) {
            val isFamous = Famous100Mountains.isFamous100(mtn.mountain.name)
            val marker = Marker().apply {
                position = LatLng(mtn.mountain.latitude, mtn.mountain.longitude)
                captionText = if (isFamous) {
                    "\u2B50 ${mtn.mountain.name}"
                } else {
                    mtn.mountain.name
                }
                captionTextSize = 12f
                if (isFamous) {
                    captionColor = ContextCompat.getColor(context, R.color.famous100_caption)
                }
                icon = OverlayImage.fromResource(R.drawable.ic_mountain_default)

                setOnClickListener {
                    markerClickListener?.invoke(mtn)
                    true
                }
                this.map = map
            }
            markers.add(marker)
            markerMountainMap[marker] = mtn
            markerOriginalPositions[marker] = marker.position

            // Load thumbnail asynchronously then update marker icon
            loadMarkerThumbnail(mtn, marker)
        }
    }

    private fun loadMarkerThumbnail(mtn: MountainWithPhotos, marker: Marker) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val photoBitmap = Glide.with(context)
                    .asBitmap()
                    .load(mtn.photos.first().uri)
                    .centerCrop()
                    .submit(120, 120)
                    .get()

                val markerBitmap = createMarkerBitmap(mtn, photoBitmap)

                withContext(Dispatchers.Main) {
                    if (marker.map != null) {
                        marker.icon = OverlayImage.fromBitmap(markerBitmap)
                    }
                }
            } catch (_: Exception) {
                // Keep default mountain icon on failure
            }
        }
    }

    private fun createMarkerBitmap(mtn: MountainWithPhotos, photoBitmap: Bitmap): Bitmap {
        val inflater = LayoutInflater.from(context)
        val isFamous = Famous100Mountains.isFamous100(mtn.mountain.name)
        return if (mtn.photoCount > 1) {
            val layoutId = if (isFamous) R.layout.view_stacked_marker_famous else R.layout.view_stacked_marker
            val view = inflater.inflate(layoutId, null)
            val iv = view.findViewById<ImageView>(R.id.ivMarkerThumb)
            val tvCount = view.findViewById<TextView>(R.id.tvPhotoCount)
            iv.setImageBitmap(photoBitmap)
            tvCount.text = "+${mtn.photoCount}"
            BitmapUtils.createBitmapFromView(view)
        } else {
            val layoutId = if (isFamous) R.layout.view_thumbnail_marker_famous else R.layout.view_thumbnail_marker
            val view = inflater.inflate(layoutId, null)
            val iv = view.findViewById<ImageView>(R.id.ivMarkerThumb)
            iv.setImageBitmap(photoBitmap)
            BitmapUtils.createBitmapFromView(view)
        }
    }

    override fun setPhotoLocationMarkers(photos: List<DevicePhoto>) {
        clearMarkers()
        val map = naverMap ?: return

        for (photo in photos) {
            val marker = Marker().apply {
                position = LatLng(photo.latitude, photo.longitude)
                captionText = ""
                icon = OverlayImage.fromResource(R.drawable.ic_mountain_default)
                this.map = map
            }
            markers.add(marker)

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val photoBitmap = Glide.with(context)
                        .asBitmap()
                        .load(photo.uri)
                        .centerCrop()
                        .submit(80, 80)
                        .get()

                    val view = LayoutInflater.from(context)
                        .inflate(R.layout.view_thumbnail_marker, null)
                    val iv = view.findViewById<ImageView>(R.id.ivMarkerThumb)
                    iv.setImageBitmap(photoBitmap)
                    val markerBitmap = BitmapUtils.createBitmapFromView(view)

                    withContext(Dispatchers.Main) {
                        if (marker.map != null) {
                            marker.icon = OverlayImage.fromBitmap(markerBitmap)
                        }
                    }
                } catch (_: Exception) {}
            }
        }
    }

    override fun clearMarkers() {
        markers.forEach { it.map = null }
        markers.clear()
        markerMountainMap.clear()
        markerOriginalPositions.clear()
    }

    override fun moveCamera(lat: Double, lng: Double, zoom: Float) {
        val cameraUpdate = CameraUpdate.scrollAndZoomTo(LatLng(lat, lng), zoom.toDouble())
        naverMap?.moveCamera(cameraUpdate)
    }

    override fun fitBoundsToLocations(photos: List<DevicePhoto>) {
        if (photos.isEmpty()) return
        val bounds = LatLngBounds.Builder().apply {
            photos.forEach { include(LatLng(it.latitude, it.longitude)) }
        }.build()
        naverMap?.moveCamera(CameraUpdate.fitBounds(bounds, 100))
    }

    override fun setOnMarkerClickListener(listener: (MountainWithPhotos) -> Unit) {
        markerClickListener = listener
    }

    override fun setOnMarkerLongClickListener(listener: (MountainWithPhotos) -> Unit) {
        markerLongClickListener = listener
    }

    override fun setOnEmptyMapLongClickListener(listener: (Double, Double) -> Unit) {
        emptyMapLongClickListener = listener
    }

    override fun setOnMarkerDragListener(listener: MapManagerInterface.OnMarkerDragResultListener?) {
        markerDragListener = listener
    }

    override fun snapMarkerBack(mountain: MountainWithPhotos) {
        for ((marker, mtn) in markerMountainMap) {
            if (mtn.mountain.id == mountain.mountain.id) {
                val originalPos = markerOriginalPositions[marker]
                if (originalPos != null) {
                    marker.position = originalPos
                }
                break
            }
        }
    }

    /**
     * 가장 가까운 마커 정보 반환 (마커, 산 데이터, 화면 거리 px)
     */
    private data class NearestMarkerInfo(
        val marker: Marker,
        val mountain: MountainWithPhotos,
        val distPx: Float
    )

    /**
     * 지도 좌표에서 가장 가까운 마커 검색
     * @param thresholdPx 이 거리(px) 이내의 마커만 대상
     */
    private fun findNearestMarkerInfo(tapLatLng: LatLng, thresholdPx: Float): NearestMarkerInfo? {
        val projection = naverMap?.projection ?: return null
        val tapPoint = projection.toScreenLocation(tapLatLng)

        var nearest: NearestMarkerInfo? = null
        var minDist = Float.MAX_VALUE

        for ((marker, mtn) in markerMountainMap) {
            val markerPoint = projection.toScreenLocation(marker.position)
            val dx = tapPoint.x - markerPoint.x
            val dy = tapPoint.y - markerPoint.y
            val dist = kotlin.math.sqrt((dx * dx + dy * dy).toDouble()).toFloat()
            if (dist < minDist && dist < thresholdPx) {
                minDist = dist
                nearest = NearestMarkerInfo(marker, mtn, dist)
            }
        }
        return nearest
    }
}
