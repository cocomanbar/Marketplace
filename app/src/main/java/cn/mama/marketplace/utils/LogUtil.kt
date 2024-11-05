package cn.mama.marketplace.utils

import android.util.Log
import cn.mama.marketplace.BuildConfig

/// 日志输出调试工具
private const val VERBOSE = 1000
private const val DEBUG = 1001
private const val INFO = 1002
private const val WARN = 1003
private const val ERROR = 1004
private const val NONE = 1010

// BuildConfig 项目需要配置出来
private val level: Int = if (BuildConfig.DEBUG) VERBOSE else NONE

fun logV(tag: String, msg: String?) {
    if (level > VERBOSE) return
    Log.v(tag, msg.toString())
}

fun logD(tag: String, msg: String?) {
    if (level > DEBUG) return
    Log.d(tag, msg.toString())
}

fun logI(tag: String, msg: String?) {
    if (level > INFO) return
    Log.i(tag, msg.toString())
}

fun logW(tag: String, msg: String?, tr: Throwable? = null) {
    if (level > WARN) return
    if (tr == null) {
        Log.w(tag, msg.toString())
    } else {
        Log.w(tag, msg.toString(), tr)
    }
}

fun logE(tag: String, msg: String?, tr: Throwable? = null) {
    if (level > ERROR) return
    Log.e(tag, msg.toString(), tr)
}
