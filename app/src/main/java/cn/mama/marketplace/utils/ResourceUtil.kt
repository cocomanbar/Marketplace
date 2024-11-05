package cn.mama.marketplace.utils

import android.content.res.Resources.Theme
import cn.mama.marketplace.App

object ResourceUtil {
    /*
    * 获取资源文件中定义的字符串
    * */
    fun getString(resId: Int): String = App.context.resources.getString(resId)

    /*
    * 获取资源文件中定义的颜色值
    * */
    fun getColorInt(resId: Int, theme: Theme? = null): Int =
        App.context.resources.getColor(resId, theme)
}