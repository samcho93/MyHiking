package com.myhiking.app.ui.map

import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.myhiking.app.data.model.DevicePhoto
import com.myhiking.app.data.model.MountainWithPhotos

interface MapManagerInterface {

    fun initialize(
        container: ViewGroup,
        fragmentManager: FragmentManager,
        callback: OnMapReadyCallback
    )

    fun destroy(fragmentManager: FragmentManager)

    fun setMountainMarkers(mountains: List<MountainWithPhotos>)

    fun setPhotoLocationMarkers(photos: List<DevicePhoto>)

    fun clearMarkers()

    fun moveCamera(lat: Double, lng: Double, zoom: Float)

    fun fitBoundsToLocations(photos: List<DevicePhoto>)

    fun setOnMarkerClickListener(listener: (MountainWithPhotos) -> Unit)

    fun setOnMarkerLongClickListener(listener: (MountainWithPhotos) -> Unit)

    /**
     * 마커가 없는 빈 지도 영역 롱프레스 시 호출 (lat, lng 전달)
     */
    fun setOnEmptyMapLongClickListener(listener: (Double, Double) -> Unit)

    /**
     * 마커 드래그 리스너 설정
     */
    fun setOnMarkerDragListener(listener: OnMarkerDragResultListener?)

    /**
     * 마커를 원래 위치로 되돌리기 (드래그 취소 시)
     */
    fun snapMarkerBack(mountain: MountainWithPhotos)

    /**
     * 마커 드래그 결과 리스너
     */
    interface OnMarkerDragResultListener {
        fun onDragStart(mountain: MountainWithPhotos)
        fun onDragEnd(mountain: MountainWithPhotos, dropLat: Double, dropLng: Double)
    }

    interface OnMapReadyCallback {
        fun onMapReady()
    }
}
