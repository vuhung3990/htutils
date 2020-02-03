package com.huutho.utilslib

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.provider.Settings
import android.text.TextUtils
import android.view.Surface
import java.io.File

/**
 * Return width of screen
 */
fun getScreenWidth(): Int {
    return Resources.getSystem().displayMetrics.widthPixels
}


/**
 * Return height of screen
 */
fun getScreenHeight(): Int {
    return Resources.getSystem().displayMetrics.heightPixels
}


/**
 * Return the density of screen.
 *
 * @return the density of screen
 */
fun getScreenDensity(): Float {
    return Resources.getSystem().displayMetrics.density
}


/**
 * Return the screen density expressed as dots-per-inch.
 *
 * @return the screen density expressed as dots-per-inch
 */
fun getScreenDensityDpi(): Int {
    return Resources.getSystem().displayMetrics.densityDpi
}


/**
 * Return the rotation of screen.
 *
 * @param activity The activity.
 * @return the rotation of screen
 */
fun getScreenRotation(activity: Activity): Int {
    return when (activity.windowManager.defaultDisplay.rotation) {
        Surface.ROTATION_0 -> 0
        Surface.ROTATION_90 -> 90
        Surface.ROTATION_180 -> 180
        Surface.ROTATION_270 -> 270
        else -> 0
    }
}


/**
 * Return whether device is tablet.
 *
 * @return `true`: yes<br></br>`false`: no
 */
fun Context.isTablet(): Boolean =
    resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK >= Configuration.SCREENLAYOUT_SIZE_LARGE


/**
 * Return whether device is rooted.
 *
 * @return `true`: yes<br></br>`false`: no
 */
fun isDeviceRooted(): Boolean {
    val su = "su"
    val locations = arrayOf(
        "/system/bin/",
        "/system/xbin/",
        "/sbin/",
        "/system/sd/xbin/",
        "/system/bin/failsafe/",
        "/data/local/xbin/",
        "/data/local/bin/",
        "/data/local/"
    )
    for (location in locations) {
        if (File(location + su).exists()) {
            return true
        }
    }
    return false
}


/**
 * Return the version name of device's system.
 *
 * @return the version name of device's system
 */
fun getSDKVersionName(): String {
    return android.os.Build.VERSION.RELEASE
}

/**
 * Return version code of device's system.
 *
 * @return version code of device's system
 */
fun getSDKVersionCode(): Int {
    return android.os.Build.VERSION.SDK_INT
}


/**
 * Return the android id of device.
 *
 * @return the android id of device
 */
@SuppressLint("HardwareIds")
fun Context.getAndroidID(): String {
    return Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID) ?: ""
}


/**
 * Return the manufacturer of the product/hardware.
 *
 * e.g. Xiaomi
 *
 * @return the manufacturer of the product/hardware
 */
fun getManufacturer(): String {
    return Build.MANUFACTURER
}


/**
 * Return the model of device.
 *
 * e.g. MI2SC
 *
 * @return the model of device
 */
fun getModel(): String {
    var model: String? = Build.MODEL
    model = if (model != null) {
        model.trim { it <= ' ' }.replace("\\s*".toRegex(), "")
    } else {
        ""
    }
    return model
}


/**
 * Return an ordered list of ABIs supported by this device. The most preferred ABI is the first
 * element in the list.
 *
 * @return an ordered list of ABIs supported by this device
 */
fun getABIs(): Array<String> {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        Build.SUPPORTED_ABIS
    } else {
        if (!TextUtils.isEmpty(Build.CPU_ABI2)) {
            arrayOf(Build.CPU_ABI, Build.CPU_ABI2)
        } else arrayOf(Build.CPU_ABI)
    }
}