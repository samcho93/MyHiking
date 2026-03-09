package com.myhiking.app.data.model

data class Mountain(
    val id: Int,
    val name: String,
    val nameEn: String,
    val latitude: Double,
    val longitude: Double,
    val altitude: Int,
    val region: String
)
