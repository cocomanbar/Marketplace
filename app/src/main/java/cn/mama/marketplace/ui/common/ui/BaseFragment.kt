package cn.mama.marketplace.ui.common.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cn.mama.marketplace.event.MessageEvent
import cn.mama.marketplace.utils.logD
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * 项目所有Fragment基类，实现一些非业务的基础功能：
 *
 *  1.Log打印
 *  2.View.Group移除
 *  3.EventBus注册通知
 */
open class BaseFragment : Fragment() {

    /**
     * Fragment 最终渲染的布局
     */
    protected var rootView: View? = null

    /**
     * 当前依附的Activity
     */
    protected lateinit var activity: Activity

    /**
     * 日志输出标记
     */
    protected val TAG: String = this.javaClass.simpleName

    /**
     * 是否注册EventBus，默认关闭
     */
    open val eventBusOpenable: Boolean = false

    /**
     * Fragment周期方法：当Fragment被加入到Activity中时调用
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)

        activity = requireActivity()
        logD(TAG, msg = "BaseFragment-onAttach")
    }

    /**
     * Fragment周期方法：创建Fragment时调用，在此可以执行一些非UI设置
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logD(TAG, msg = "BaseFragment-onCreate")

        if (eventBusOpenable) {
            registerEventBus()
        }
    }

    /**
     * Fragment周期方法：创建Fragment的布局
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        assert(false) { "调用错误，请调用-onCreateViewLayoutIfNeeded:" }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    /**
     * Fragment周期方法：创建Fragment的布局时调用，返回布局的View
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logD(TAG, "BaseFragment-onViewCreated")
    }

    /**
     * Fragment周期方法：当Fragment变为用户可见时调用
     */
    override fun onStart() {
        super.onStart()
        logD(TAG, "BaseFragment-onStart")
    }

    /**
     * Fragment周期方法：当Fragment开始与用户交互时调用
     */
    override fun onResume() {
        super.onResume()
        logD(TAG, "BaseFragment-onResume")
    }

    /**
     * Fragment周期方法：当Fragment停止与用户交互时调用
     */
    override fun onPause() {
        super.onPause()
        logD(TAG, "BaseFragment-onPause")
    }

    /**
     * Fragment周期方法：当Fragment不再被用户可见时调用
     */
    override fun onStop() {
        super.onStop()
        logD(TAG, "BaseFragment-onStop")
    }

    /**
     * Fragment周期方法：当Fragment的布局被移除时调用
     */
    override fun onDestroyView() {
        super.onDestroyView()
        logD(TAG, "BaseFragment-onDestroyView")
        unRegisterEventBus()
        if (rootView?.parent != null) (rootView?.parent as ViewGroup).removeView(rootView)
    }

    /**
     * Fragment周期方法：当Fragment销毁时调用，可以进行资源回收
     */
    override fun onDestroy() {
        super.onDestroy()
        logD(TAG, "BaseFragment-onDestroy")
    }

    /**
     * Fragment周期方法:
     * 当Fragment从Activity中移除时调用
     */
    override fun onDetach() {
        super.onDetach()
        logD(TAG, "BaseFragment-onDetach")
    }

    /**
     * 在Fragment基类中获取通用的控件，会将传入的[view]实例，再原封不动传回。为了赋值[rootView]组件
     */
    open fun onCreateViewLayoutIfNeeded(view: View): View {
        rootView = view
        return view
    }

    /**
     * 添加Bus监听器
     */
    private fun registerEventBus() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
    }

    /**
     * 移除Bus监听器
     */
    private fun unRegisterEventBus() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
    }

    /**
     * Bus通知，子类实现对消息[messageEvent]的解析，注意避免耗时任务ANR
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    open fun onMessageEvent(messageEvent: MessageEvent) {

    }
}