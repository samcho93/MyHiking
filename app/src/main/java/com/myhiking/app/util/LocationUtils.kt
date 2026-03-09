package com.myhiking.app.util

import kotlin.math.*

object LocationUtils {

    private const val EARTH_RADIUS_METERS = 6371000.0

    fun distanceInMeters(lat1: Double, lng1: Double, lat2: Double, lng2: Double): Double {
        val dLat = Math.toRadians(lat2 - lat1)
        val dLng = Math.toRadians(lng2 - lng1)
        val a = sin(dLat / 2).pow(2) +
                cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) *
                sin(dLng / 2).pow(2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        return EARTH_RADIUS_METERS * c
    }

    fun isWithinRadius(
        lat1: Double, lng1: Double,
        lat2: Double, lng2: Double,
        radiusMeters: Double
    ): Boolean {
        return distanceInMeters(lat1, lng1, lat2, lng2) <= radiusMeters
    }
}
