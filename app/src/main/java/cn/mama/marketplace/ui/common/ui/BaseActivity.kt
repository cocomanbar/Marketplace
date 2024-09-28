package cn.mama.marketplace.ui.common.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import cn.mama.marketplace.R
import cn.mama.marketplace.event.MessageEvent
import cn.mama.marketplace.utils.ActivityCollector
import cn.mama.marketplace.utils.logD
import com.gyf.immersionbar.ImmersionBar
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.lang.ref.WeakReference


/// 应用程序中所有Activity的基类
open class BaseActivity : AppCompatActivity() {

    // 判断当前Activity是否在前台
    protected var isActive: Boolean = false

    // 当前Activity实例
    private var activity: Activity? = null

    // 弱引用当前Activity实例
    private var activityWR: WeakReference<Activity>? = null

    // 日志标记
    private val TAG: String = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        logD(TAG, msg = "BaseActivity-onCreate")

        activity = this
        activityWR = WeakReference(activity!!)
        ActivityCollector.pushTask(activityWR)

        EventBus.getDefault().register(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        logD(TAG, msg = "BaseActivity-onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        logD(TAG, msg = "BaseActivity-onRestoreInstanceState")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        logD(TAG, msg = "BaseActivity-onNewIntent")
    }

    override fun onRestart() {
        super.onRestart()

        logD(TAG, msg = "BaseActivity-onRestart")
    }

    override fun onStart() {
        super.onStart()

        logD(TAG, msg = "BaseActivity-onStart")
    }

    override fun onResume() {
        super.onResume()

        logD(TAG, msg = "BaseActivity-onResume")
        isActive = true
    }

    override fun onPause() {
        super.onPause()

        logD(TAG, msg = "BaseActivity-onPause")
        isActive = false
    }

    override fun onStop() {
        super.onStop()

        logD(TAG, msg = "BaseActivity-onStop")
    }

    override fun onDestroy() {
        super.onDestroy()

        logD(TAG, msg = "BaseActivity-onDestroy")

        activity = null
        ActivityCollector.removeTask(activityWR)
        EventBus.getDefault().unregister(this)
    }

    override fun setContentView(view: View?) {
        super.setContentView(view)

        setStatusBarBackground(R.color.colorTextPrimaryDark)
        setupViews()
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)

        setStatusBarBackground(R.color.colorTextPrimaryDark)
        setupViews()
    }

    /// 初始化View
    protected open fun setupViews() {

    }

    /// 设置状态栏背景颜色
    open fun setStatusBarBackground(@ColorRes statusBarColor: Int) {
        // 第三方库，它提供了一种简单的方式来实现沉浸式状态栏和底部栏（导航栏）的兼容性处理
        // https://www.jianshu.com/p/df49d2f3bfd4
        ImmersionBar.with(this)
            // 自动状态栏字体颜色切换，第二个参数是判断状态栏深色模式的亮度阈值
            .autoStatusBarDarkModeEnable(true, 0.2f)
            // 设置状态栏的颜色
            .statusBarColor(statusBarColor)
            // 指定是否遵守系统窗口的窗口模式，true表示遵守，这样可以对状态栏进行透明处理
            .fitsSystemWindows(true)
            // 初始化
            .init()
    }

    /// 消息通知
    @Subscribe(threadMode = ThreadMode.MAIN)
    open fun onMessageEvent(messageEvent: MessageEvent) {

    }
}