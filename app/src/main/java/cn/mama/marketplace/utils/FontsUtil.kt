package cn.mama.marketplace.utils

import android.graphics.Typeface
import cn.mama.marketplace.App

object FontsUtil {

    const val FZLL_TYPEFACE = 1
    const val FZDB1_TYPEFACE = 2
    const val FUTURA_TYPEFACE = 3
    const val DIN_TYPEFACE = 4
    const val LOBSTER_TYPEFACE = 5

    val fzllTypeFace: Typeface by lazy {
        // Typeface 用于定义字体的外观和风格
        Typeface.createFromAsset(App.context.assets, "fonts/FZLanTingHeiS-L-GB-Regular.TTF")
    }

    val fzdb1Typeface: Typeface by lazy {
        Typeface.createFromAsset(App.context.assets, "fonts/FZLanTingHeiS-DB1-GB-Regular.TTF")
    }

    val futuraTypeface: Typeface by lazy {
        Typeface.createFromAsset(App.context.assets, "fonts/Futura-CondensedMedium.TTF")
    }

    val dinTypeface: Typeface by lazy {
        Typeface.createFromAsset(App.context.assets, "fonts/DIN-Condensed-Bold.ttf")
    }

    val lobsterTypeface: Typeface by lazy {
        Typeface.createFromAsset(App.context.assets, "fonts/Lobster-1.4.otf")
    }

    fun turnTypeface(typeface: Int?): Typeface? {
        return when (typeface) {
            FZLL_TYPEFACE -> fzllTypeFace
            FZDB1_TYPEFACE -> fzdb1Typeface
            FUTURA_TYPEFACE -> futuraTypeface
            DIN_TYPEFACE -> dinTypeface
            LOBSTER_TYPEFACE -> lobsterTypeface
            else -> Typeface.DEFAULT
        }
    }
}