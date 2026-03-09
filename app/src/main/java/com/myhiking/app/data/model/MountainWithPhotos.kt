package com.myhiking.app.data.model

data class MountainWithPhotos(
    val mountain: Mountain,
    val photos: List<DevicePhoto>
) {
    val hasPhotos: Boolean get() = photos.isNotEmpty()
    val photoCount: Int get() = photos.size
}
