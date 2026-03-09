package com.myhiking.app.ui.map

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.myhiking.app.R
import com.myhiking.app.data.model.DevicePhoto
import com.myhiking.app.data.model.MountainWithPhotos
import com.myhiking.app.data.source.Famous100Mountains
import com.myhiking.app.util.BitmapUtils
import androidx.core.content.ContextCompat
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

    companion object {
        private const val MAP_FRAGMENT_TAG = "naver_map"
        private val KOREA_CENTER = LatLng(36.5, 127.5)
        private const val DEFAULT_ZOOM = 7f
        private val KOREA_BOUNDS = LatLngBounds(
            LatLng(33.0, 124.5),
            LatLng(38.6, 131.9)
        )
    }

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

            // Long-press: 마커가 가까이 있으면 마커 롱프레스, 없으면 빈 지도 롱프레스
            map.setOnMapLongClickListener { _, latLng ->
                val nearestMtn = findNearestMarkerMountain(latLng)
                if (nearestMtn != null) {
                    markerLongClickListener?.invoke(nearestMtn)
                } else {
                    emptyMapLongClickListener?.invoke(latLng.latitude, latLng.longitude)
                }
            }

            callback.onMapReady()
        }
    }

    override fun destroy(fragmentManager: FragmentManager) {
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

    /**
     * 지도 좌표에서 가장 가까운 마커의 산 정보 반환 (long-press 감지용)
     * 화면상 50dp 이내의 마커만 대상
     */
    private fun findNearestMarkerMountain(tapLatLng: LatLng): MountainWithPhotos? {
        val projection = naverMap?.projection ?: return null
        val tapPoint = projection.toScreenLocation(tapLatLng)

        var nearestMtn: MountainWithPhotos? = null
        var minDist = Float.MAX_VALUE
        val thresholdPx = 50 * context.resources.displayMetrics.density

        for ((marker, mtn) in markerMountainMap) {
            val markerPoint = projection.toScreenLocation(marker.position)
            val dx = tapPoint.x - markerPoint.x
            val dy = tapPoint.y - markerPoint.y
            val dist = kotlin.math.sqrt((dx * dx + dy * dy).toDouble()).toFloat()
            if (dist < minDist && dist < thresholdPx) {
                minDist = dist
                nearestMtn = mtn
            }
        }
        return nearestMtn
    }
}
