package cn.mama.marketplace.utils

import android.app.Activity
import android.os.Build
import android.view.View
import android.view.WindowInsetsController
import androidx.annotation.ColorInt


/**
 * 状态栏管理类
 */
class StatusBarUtil(private val activity: Activity) {

    private val TAG: String = "StatusBarUtil"

    /**
     * 设置状态栏的背景颜色[color]
     */
    fun setStatusBarColor(@ColorInt color: Int) {
        // Android 5.0（API 级别 21）及以上版本有效
        activity.window.statusBarColor = color
    }

    /**
     * 设置状态栏图标为深色
     */
    fun makeDarkMode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // 对于 Android 11（API 级别 30）及以上版本
            val controller = activity.window.insetsController
            controller?.systemBarsBehavior =
                WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        } else {
            // 对于 Android 6.0（API 级别 23）及以上版本
            activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }

    /**
     * 设置状态栏图标为浅色
     */
    fun makeLightMode() {
        activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
    }
}