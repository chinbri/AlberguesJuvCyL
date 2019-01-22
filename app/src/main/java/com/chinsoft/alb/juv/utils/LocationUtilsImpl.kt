package com.chinsoft.alb.juv.utils

import android.content.Context
import android.location.Geocoder
import android.location.Location
import com.chinsoft.domain.util.LocationUtils
import javax.inject.Inject
import kotlin.math.round

class LocationUtilsImpl @Inject constructor(val context: Context): LocationUtils {

    override fun getDistance(destinationLatitude: Double,
                             destinationLongitude: Double,
                             currentLatitude: Double,
                             currentLongitude: Double): Float{

        val destinationLocation = Location("")
        destinationLocation.longitude = destinationLongitude
        destinationLocation.latitude = destinationLatitude

        val currentLocation = Location("")
        currentLocation.longitude = currentLongitude
        currentLocation.latitude = currentLatitude

        return currentLocation.distanceTo(destinationLocation)/1000

    }

    override fun getLocationFromAddress(addressInputString: String): LocationUtils.GeoPoint?{
        try {
            val addressList = Geocoder(context).getFromLocationName(addressInputString,5)
            if(addressList.size > 0){
                return LocationUtils.GeoPoint(
                    addressList[0].latitude.round(6),
                    addressList[0].longitude.round(6)
                )
            }
        }catch (e: Exception){
        }
        return null
    }

    fun Double.round(decimals: Int): Double {
        var multiplier = 1.0
        repeat(decimals) { multiplier *= 10 }
        return round(this * multiplier) / multiplier
    }

}