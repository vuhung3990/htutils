package com.huutho.utilslib

import android.util.Log

fun getLogInfo(): Array<String> {
    val stackTrace = Thread.currentThread().stackTrace
    return arrayOf(
        stackTrace[5].className.substringAfterLast("."),
        stackTrace[5].methodName,
        stackTrace[5].lineNumber.toString()
    )
}

fun Any.printV(message: String, tag: String? = null) {
    val logInfo = getLogInfo()
        val myTag = tag ?: "[${logInfo[2]}.${logInfo[0]}.${logInfo[1]}]"
        Log.v(myTag, message)
}

fun Any.printV(message: () -> String) {
    val logInfo = getLogInfo()
    Log.v("[${logInfo[2]}.${logInfo[0]}.${logInfo[1]}]", message.invoke())
}

/**************************************************************************************/


fun Any.printD(message: String, tag: String? = null) {
    val logInfo = getLogInfo()
    val myTag = tag ?: "[${logInfo[2]}.${logInfo[0]}.${logInfo[1]}]"
    Log.d(myTag, message)
}

fun Any.printD(message: () -> String) {
    val logInfo = getLogInfo()
    Log.d("[${logInfo[2]}.${logInfo[0]}.${logInfo[1]}]", message.invoke())
}

/**************************************************************************************/


fun Any.printI(message: String, tag: String? = null) {
    val logInfo = getLogInfo()
    val myTag = tag ?: "[${logInfo[2]}.${logInfo[0]}.${logInfo[1]}]"
    Log.i(myTag, message)
}

fun Any.printI(message: () -> String) {
    val logInfo = getLogInfo()
    Log.i("[${logInfo[2]}.${logInfo[0]}.${logInfo[1]}]", message.invoke())
}

/**************************************************************************************/


fun Any.printW(message: String, tag: String? = null) {
    val logInfo = getLogInfo()
    val myTag = tag ?: "[${logInfo[2]}.${logInfo[0]}.${logInfo[1]}]"
    Log.w(myTag, message)
}

fun Any.printW(message: () -> String) {
    val logInfo = getLogInfo()
    Log.w("[${logInfo[2]}.${logInfo[0]}.${logInfo[1]}]", message.invoke())
}

/**************************************************************************************/


fun Any.printE(message: String, tag: String? = null) {
    val logInfo = getLogInfo()
    val myTag = tag ?: "[${logInfo[2]}.${logInfo[0]}.${logInfo[1]}]"
    Log.e(myTag, message)
}

fun Any.printE(message: () -> String) {
    val logInfo = getLogInfo()
    Log.e("[${logInfo[2]}.${logInfo[0]}.${logInfo[1]}]", message.invoke())
}
