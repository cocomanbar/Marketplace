package cn.mama.marketplace.utils

import android.app.Activity
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import cn.mama.marketplace.R
import com.gyf.immersionbar.ImmersionBar


/**
 * 状态栏管理类
 */
class StatusBarUtil(private val activity: Activity) {

    /**
     * 状态栏以及导航栏处理类
     */
    private val mImmersionBar by lazy {
        ImmersionBar.with(this.activity)
    }

    /**
     * 普通样式的状态栏颜色
     */
    protected val normalBarColor by lazy {
        R.color.white
    }

    /**
     * 设置通用模式的状态
     */
    fun setCommonDarkStyle() {
        mImmersionBar
            // 启用状态栏颜色自动切换，第二个参数是判断状态栏深色模式的亮度阈值
            .autoStatusBarDarkModeEnable(true, 0.2f)
            // 状态栏颜色
            .statusBarColor(normalBarColor)
            // 指定是否遵守系统窗口的窗口模式，true表示遵守，这样可以对状态栏进行透明处理
            .fitsSystemWindows(true)
            // 设置状态栏图标为深色
            .statusBarDarkFont(true)
            // 应用参数
            .init()
    }

    /**
     * 透明导航栏
     */
    fun makeTransparentStatusBar() {
        mImmersionBar
            .transparentStatusBar()
            .init()
    }

    /**
     * 设置状态栏的背景颜色[color]
     */
    fun setStatusBarColor(color: Int) {
        mImmersionBar
            .statusBarColor(color)
            .init()
    }

    /**
     * 设置导航栏的背景颜色[color]
     */
    fun setNavigationBarColor(color: Int) {
        mImmersionBar
            .navigationBarColor(color)
            .init()
    }

    /**
     * 设置状态栏图标为深色
     */
    fun makeDarkMode() {
        mImmersionBar
            .statusBarDarkFont(true)
            .init()
    }

    /**
     * 设置状态栏图标为浅色
     */
    fun makeLightMode() {
        mImmersionBar
            .statusBarDarkFont(false)
            .init()
    }
}