package com.huutho.utilslib

import android.text.format.DateUtils
import java.util.*

fun Long.isToday(): Boolean {
    if (this <= 0) {
        return false
    }
    return DateUtils.isToday(this * 1000)
}


fun Date.isPastTime(): Boolean {
    val calNow = Calendar.getInstance()
    return this.time < calNow.time.time
}


/**
 * Compare day between 2 [Date]. This is difference with
 * [Date.after] or [Date.before]. Because it only
 * compare by day (not compare hour, minutes, seconds).

 * @param secondDateRoot    This is secondDateRoot rootDate
 * *
 * @param secondDateCompare seconds Date compare to secondDateRoot .
 * *
 * @return -1 if dateCompare < dateRoot, 0 if equal, 1 if dateCompare > dateRoot.
 */
fun compareDateWithSeconds(secondDateRoot: Long, secondDateCompare: Long): Int {
    val calendarRoot = Calendar.getInstance()
    calendarRoot.timeInMillis = secondDateRoot * 1000L

    val calenderCompare = Calendar.getInstance()
    calenderCompare.timeInMillis = secondDateCompare * 1000L

    val rootYear = calendarRoot.get(Calendar.YEAR)
    val compareYear = calenderCompare.get(Calendar.YEAR)
    return when {
        compareYear < rootYear -> -1
        compareYear > rootYear -> 1
        else -> {
            val rootDayOfYear = calendarRoot.get(Calendar.DAY_OF_YEAR)
            val compareDayOfYear = calenderCompare.get(Calendar.DAY_OF_YEAR)
            when {
                compareDayOfYear < rootDayOfYear -> -1
                compareDayOfYear > rootDayOfYear -> 1
                else -> 0
            }
        }
    }
}


/**
 * Compare day between 2 [Date]. This is difference with
 * [Date.after] or [Date.before]. Because it only
 * compare by day (not compare hour, minutes, seconds).

 * @param dateRoot    This is rootDate
 * *
 * @param dateCompare Date compare to rootDate.
 * *
 * @return -1 if dateCompare < dateRoot, 0 if equal, 1 if dateCompare = dateRoot.
 */
fun compareDate(dateRoot: Date, dateCompare: Date): Int {
    val calendarRoot = Calendar.getInstance()
    calendarRoot.time = dateRoot

    val calenderCompare = Calendar.getInstance()
    calenderCompare.time = dateCompare
    val rootYear = calendarRoot.get(Calendar.YEAR)
    val compareYear = calenderCompare.get(Calendar.YEAR)
    return when {
        compareYear < rootYear -> -1
        compareYear > rootYear -> 1
        else -> {
            val rootDayOfYear = calendarRoot.get(Calendar.DAY_OF_YEAR)
            val compareDayOfYear = calenderCompare.get(Calendar.DAY_OF_YEAR)
            when {
                compareDayOfYear < rootDayOfYear -> -1
                compareDayOfYear > rootDayOfYear -> 1
                else -> 0
            }
        }
    }
}