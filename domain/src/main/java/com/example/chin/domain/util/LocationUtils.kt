package com.example.chin.domain.util

interface LocationUtils {

    data class GeoPoint(val latitude: Double, val longitude: Double)

    fun getDistance(destinationLatitude: Double, destinationLongitude: Double, currentLatitude: Double, currentLongitude: Double): Float

    fun getLocationFromAddress(addressInputString: String): GeoPoint?

}