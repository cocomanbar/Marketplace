package cn.mama.marketplace.utils

import cn.mama.marketplace.App

object GlobalUtil {

    private val TAG = "GlobalUtil"

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

    /*
    * 获取资源文件中定义的字符串
    * */
    fun getResourceString(resId: Int): String = App.context.resources.getString(resId)
}