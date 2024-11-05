package cn.mama.marketplace.utils

import android.os.Build
import cn.mama.marketplace.App

/**
 * App工程信息获取类
 */
object ProjectUtil {

    /*
    * 获取应用程序的包名
    * */
    val appPackage: String
        get() = App.context.packageName

    /*
    * 获取当前应用程序的名称
    * */
    val appName: String
        get() = App.context.resources.getString(App.context.applicationInfo.labelRes)

    /**
     * 获取当前设备的系统版本号，如: "8.1.0"
     */
    val osVersion: String
        get() = Build.VERSION.RELEASE

    /**
     * 获取当前设备的SDK版本号
     */
    val sdkVersion: Int
        get() = Build.VERSION.SDK_INT
}