package cn.mama.marketplace.ui.common.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import cn.mama.marketplace.event.MessageEvent
import cn.mama.marketplace.utils.ActivityCollector
import cn.mama.marketplace.utils.StatusBarUtil
import cn.mama.marketplace.utils.logD
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.lang.ref.WeakReference


/**
 * 应用程序中所有Activity的基类
 */
open class BaseActivity : AppCompatActivity() {


    /**
     * 是否注册EventBus，默认关闭
     */
    open val eventBusOpenable: Boolean = false

    /**
     * 监听返回事件
     */
    open val onBackPressable: Boolean = false

    /**
     * 判断当前Activity是否在前台
     */
    protected var isActive: Boolean = false

    /**
     * 当前Activity实例
     */
    private var activity: Activity? = null

    /**
     * 弱引用当前Activity实例
     */
    private var activityWR: WeakReference<Activity>? = null

    /**
     * 日志标记
     */
    private val TAG: String = this.javaClass.simpleName

    /**
     * 返回按钮事件，通过[onBackPressable]控制是否拦截返回事件
     */
    private val onBackPressedCallback by lazy {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onHandleOnBackPressed()
            }
        }
    }

    /**
     *  导航栏的管理类
     */
    protected val statusBarUtil by lazy {
        StatusBarUtil(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logD(TAG, msg = "BaseActivity-onCreate")

        activity = this
        activityWR = WeakReference(activity!!)
        ActivityCollector.pushActivity(activityWR)

        if (eventBusOpenable && !EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }

        if (onBackPressable) {
            onBackPressedDispatcher.addCallback(onBackPressedCallback)
        }
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
        ActivityCollector.removeActivity(activityWR)
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
        if (onBackPressable) {
            onBackPressedCallback.remove()
        }
    }

    override fun setContentView(view: View?) {
        super.setContentView(view)
        statusBarUtil.setCommonDarkStyle()
        setupViews()
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        statusBarUtil.setCommonDarkStyle()
        setupViews()
    }

    /**
     * 初始化View
     */
    protected open fun setupViews() {

    }

    /**
     * 处理物理返回事件的逻辑，前提是[onBackPressable]属性重载为true
     */
    open fun onHandleOnBackPressed() {

    }

    /**
     * 消息通知
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    open fun onMessageEvent(messageEvent: MessageEvent) {

    }
}