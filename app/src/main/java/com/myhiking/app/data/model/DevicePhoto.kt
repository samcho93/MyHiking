package com.myhiking.app.data.model

import android.net.Uri

data class DevicePhoto(
    val id: Long,
    val uri: Uri,
    val latitude: Double,
    val longitude: Double,
    val altitude: Double,
    val dateTaken: Long,
    val displayName: String,
    val size: Long
)
