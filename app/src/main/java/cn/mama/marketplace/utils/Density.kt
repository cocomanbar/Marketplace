package cn.mama.marketplace.utils

import cn.mama.marketplace.App

/*
* 获取当前设备的宽
* */
val screenWidth: Int
    get() = App.context.resources.displayMetrics.widthPixels

/*
* 获取当前设备的高
* */
val screenHeight: Int
    get() = App.context.resources.displayMetrics.heightPixels

/*
* 获取当前手机的分辨率
* */
val screenScale: Float
    get() = App.context.resources.displayMetrics.density

/*
* 获取宽高比的字符串格式
* */
val screenPixel: String
    get() = "${screenWidth}x${screenHeight}"


/*
* 根据手机分辨率将dp转成px
* */
fun dp2px(dp: Float): Int = (dp * screenScale + 0.5f).toInt()

/*
* 根据手机分辨率将px转成dp
* */
fun px2dp(px: Int): Int = (px / screenScale + 0.5f).toInt()


