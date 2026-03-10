package com.myhiking.app.ui.map

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.myhiking.app.R
import com.myhiking.app.data.model.DevicePhoto
import com.myhiking.app.data.model.MountainWithPhotos
import com.myhiking.app.data.source.Famous100Mountains
import com.myhiking.app.util.BitmapUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GoogleMapManager(private val context: Context) : MapManagerInterface {

    private var googleMap: GoogleMap? = null
    private var mapFragment: SupportMapFragment? = null
    private var markerClickListener: ((MountainWithPhotos) -> Unit)? = null
    private var markerLongClickListener: ((MountainWithPhotos) -> Unit)? = null
    private var emptyMapLongClickListener: ((Double, Double) -> Unit)? = null
    private val markerDataMap = mutableMapOf<String, MountainWithPhotos>()
    private var markerDragListener: MapManagerInterface.OnMarkerDragResultListener? = null
    private val markerOriginalPositions = mutableMapOf<String, LatLng>()
    private val markerObjectMap = mutableMapOf<String, com.google.android.gms.maps.model.Marker>()

    companion object {
        private const val MAP_FRAGMENT_TAG = "google_map"
    }

    override fun initialize(
        container: ViewGroup,
        fragmentManager: FragmentManager,
        callback: MapManagerInterface.OnMapReadyCallback
    ) {
        mapFragment = SupportMapFragment.newInstance()
        fragmentManager.beginTransaction()
            .replace(container.id, mapFragment!!, MAP_FRAGMENT_TAG)
            .commitAllowingStateLoss()

        mapFragment?.getMapAsync { map ->
            googleMap = map
            map.uiSettings.isZoomControlsEnabled = true
            map.uiSettings.isCompassEnabled = true

            map.setOnMarkerClickListener { marker ->
                val mtn = markerDataMap[marker.id]
                if (mtn != null) {
                    markerClickListener?.invoke(mtn)
                    true
                } else {
                    false
                }
            }

            // Long-press: 마커가 가까이 있으면 마커 롱프레스, 없으면 빈 지도 롱프레스
            map.setOnMapLongClickListener { latLng ->
                val nearestMtn = findNearestMarkerMountain(latLng)
                if (nearestMtn != null) {
                    markerLongClickListener?.invoke(nearestMtn)
                } else {
                    emptyMapLongClickListener?.invoke(latLng.latitude, latLng.longitude)
                }
            }

            // 마커 드래그 리스너
            map.setOnMarkerDragListener(object : GoogleMap.OnMarkerDragListener {
                override fun onMarkerDragStart(marker: com.google.android.gms.maps.model.Marker) {
                    val mtn = markerDataMap[marker.id] ?: return
                    markerDragListener?.onDragStart(mtn)
                }
                override fun onMarkerDrag(marker: com.google.android.gms.maps.model.Marker) { /* SDK가 처리 */ }
                override fun onMarkerDragEnd(marker: com.google.android.gms.maps.model.Marker) {
                    val mtn = markerDataMap[marker.id] ?: return
                    val pos = marker.position
                    markerDragListener?.onDragEnd(mtn, pos.latitude, pos.longitude)
                }
            })

            callback.onMapReady()
        }
    }

    override fun destroy(fragmentManager: FragmentManager) {
        clearMarkers()
        mapFragment?.let {
            fragmentManager.beginTransaction().remove(it).commitAllowingStateLoss()
        }
        mapFragment = null
        googleMap = null
        markerDataMap.clear()
    }

    override fun setMountainMarkers(mountains: List<MountainWithPhotos>) {
        clearMarkers()
        val map = googleMap ?: return

        val mountainsWithPhotos = mountains.filter { it.hasPhotos }

        for (mtn in mountainsWithPhotos) {
            val isFamous = Famous100Mountains.isFamous100(mtn.mountain.name)
            val displayName = if (isFamous) {
                "\u2B50 ${mtn.mountain.name}"
            } else {
                mtn.mountain.name
            }
            val markerOptions = MarkerOptions()
                .position(LatLng(mtn.mountain.latitude, mtn.mountain.longitude))
                .title(displayName)
                .draggable(true)

            val marker = map.addMarker(markerOptions)
            if (marker != null) {
                markerDataMap[marker.id] = mtn
                markerOriginalPositions[marker.id] = marker.position
                markerObjectMap[marker.id] = marker

                // Load thumbnail asynchronously
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
                            marker.setIcon(BitmapDescriptorFactory.fromBitmap(markerBitmap))
                        }
                    } catch (_: Exception) {}
                }
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
        val map = googleMap ?: return

        for (photo in photos) {
            val markerOptions = MarkerOptions()
                .position(LatLng(photo.latitude, photo.longitude))
                .title(photo.displayName)

            val marker = map.addMarker(markerOptions) ?: continue

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
                        marker.setIcon(BitmapDescriptorFactory.fromBitmap(markerBitmap))
                    }
                } catch (_: Exception) {}
            }
        }
    }

    override fun clearMarkers() {
        googleMap?.clear()
        markerDataMap.clear()
        markerOriginalPositions.clear()
        markerObjectMap.clear()
    }

    override fun moveCamera(lat: Double, lng: Double, zoom: Float) {
        googleMap?.moveCamera(
            CameraUpdateFactory.newLatLngZoom(LatLng(lat, lng), zoom)
        )
    }

    override fun fitBoundsToLocations(photos: List<DevicePhoto>) {
        if (photos.isEmpty()) return
        val bounds = LatLngBounds.Builder().apply {
            photos.forEach { include(LatLng(it.latitude, it.longitude)) }
        }.build()
        googleMap?.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100))
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
        for ((markerId, mtn) in markerDataMap) {
            if (mtn.mountain.id == mountain.mountain.id) {
                val originalPos = markerOriginalPositions[markerId]
                val markerObj = markerObjectMap[markerId]
                if (originalPos != null && markerObj != null) {
                    markerObj.position = originalPos
                }
                break
            }
        }
    }

    private fun findNearestMarkerMountain(tapLatLng: LatLng): MountainWithPhotos? {
        val map = googleMap ?: return null
        val projection = map.projection
        val tapPoint = projection.toScreenLocation(tapLatLng)

        var nearestMtn: MountainWithPhotos? = null
        var minDist = Float.MAX_VALUE
        val thresholdPx = 50 * context.resources.displayMetrics.density

        for ((_, mtn) in markerDataMap) {
            val markerPoint = projection.toScreenLocation(
                LatLng(mtn.mountain.latitude, mtn.mountain.longitude)
            )
            val dx = (tapPoint.x - markerPoint.x).toFloat()
            val dy = (tapPoint.y - markerPoint.y).toFloat()
            val dist = kotlin.math.sqrt((dx * dx + dy * dy).toDouble()).toFloat()
            if (dist < minDist && dist < thresholdPx) {
                minDist = dist
                nearestMtn = mtn
            }
        }
        return nearestMtn
    }
}
