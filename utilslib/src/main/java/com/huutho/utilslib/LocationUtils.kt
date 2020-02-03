package com.huutho.utilslib

import android.content.Context
import android.location.LocationManager


/**
 * @param context
 * *
 * @return true if gps enabled or location network service enabled.
 */
fun Context.isLocationServiceEnabled(): Boolean {
    val gpsEnabled: Boolean
    val networkEnabled: Boolean
    val lm = getSystemService(Context.LOCATION_SERVICE) as LocationManager
    try {
        gpsEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
        networkEnabled = lm
            .isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    } catch (ex: Exception) {
        ex.printStackTrace()
        return false
    }

    return gpsEnabled || networkEnabled
}

/**
 * @param context
 * *
 * @return true if gps enabled.
 */
fun Context.isGPSEnabled(): Boolean {
    val lm = getSystemService(Context.LOCATION_SERVICE) as LocationManager
    try {
        return lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
    } catch (ex: Exception) {
        ex.printStackTrace()
    }

    return false
}

/**
 * @param context
 * *
 * @return true if location service network enabled.
 */
fun Context.isLocationServiceNetworkEnabled(): Boolean {
    val lm = getSystemService(Context.LOCATION_SERVICE) as LocationManager
    try {
        return lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    } catch (ex: Exception) {
        ex.printStackTrace()
    }

    return false
}