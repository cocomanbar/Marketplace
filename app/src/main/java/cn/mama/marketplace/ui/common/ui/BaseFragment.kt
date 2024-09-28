package cn.mama.marketplace.ui.common.ui

import android.app.Activity
import android.content.Context
import android.nfc.Tag
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import android.widget.TextView
import androidx.fragment.app.Fragment
import cn.mama.marketplace.R
import cn.mama.marketplace.event.MessageEvent
import cn.mama.marketplace.utils.logD
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

interface FragmentRequestLifecycle {
    fun startLoading()

    fun loadFinished()

    fun loadFailed(msg: String?)
}

open class BaseFragment : Fragment(), FragmentRequestLifecycle {

    // 是否已经加载过数据
    private var mHasLoadedData: Boolean = false

    // Fragment 最终渲染的布局
    protected var rootView: View? = null

    // Fragment 加载等待的布局
    protected var loadingView: View? = null

    // Fragment 加载失败的布局
    protected var errorView: View? = null

    // 当前依附的Activity
    lateinit var activity: Activity

    // 日志输出标记
    protected val TAG: String = this.javaClass.simpleName


    /// 当Fragment被加入到Activity中时调用
    override fun onAttach(context: Context) {
        super.onAttach(context)

        activity = requireActivity()
        logD(TAG, msg = "BaseFragment-onAttach")
    }

    /// 创建Fragment时调用，在此可以执行一次性设置
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logD(TAG, msg = "BaseFragment-onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        logD(TAG, "BaseFragment-onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    /// 创建Fragment的布局时调用，返回布局的View
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logD(TAG, "BaseFragment-onViewCreated")
    }

    /// 当Fragment变为用户可见时调用
    override fun onStart() {
        super.onStart()
        logD(TAG, "BaseFragment-onStart")
    }

    /// 当Fragment开始与用户交互时调用
    override fun onResume() {
        super.onResume()
        logD(TAG, "BaseFragment-onResume")
        //当Fragment在屏幕上可见并且没有加载过数据时调用
        if (!mHasLoadedData) {
            onNetwork()
            logD(TAG, "BaseFragment-->loadDataOnce")
            mHasLoadedData = true
        }
    }

    /// 当Fragment停止与用户交互时调用
    override fun onPause() {
        super.onPause()
        logD(TAG, "BaseFragment-onPause")
    }

    /// 当Fragment不再被用户可见时调用
    override fun onStop() {
        super.onStop()
        logD(TAG, "BaseFragment-onStop")
    }

    /// 当Fragment的布局被移除时调用
    override fun onDestroyView() {
        super.onDestroyView()
        logD(TAG, "BaseFragment-onDestroyView")
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
        if (rootView?.parent != null) (rootView?.parent as ViewGroup).removeView(rootView)
    }

    /// 当Fragment销毁时调用，可以进行资源回收
    override fun onDestroy() {
        super.onDestroy()
        logD(TAG, "BaseFragment-onDestroy")
    }

    /// 当Fragment从Activity中移除时调用
    override fun onDetach() {
        super.onDetach()
        logD(TAG, "BaseFragment-onDetach")
    }

    /// 在Fragment基类中获取通用的控件，会将传入的View实例原封不动返回
    fun onCreateView(view: View): View {
        rootView = view
        loadingView = view.findViewById(R.id.loading)
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
        return view
    }

    /// 开始加载，显示加载布局
    override fun startLoading() {
        showLoadingView()
        hideErrorView()
    }

    /// 加载完成，将加载控件隐藏
    override fun loadFinished() {
        hideLoadingView()
    }

    override fun loadFailed(msg: String?) {
        hideLoadingView()
    }

    /// 隐藏加载布局
    protected fun hideLoadingView() {
        loadingView?.visibility = View.GONE
    }

    /// 显示加载布局
    protected fun showLoadingView() {
        loadingView?.visibility = View.VISIBLE
    }

    /// 隐藏异常布局
    protected fun hideErrorView() {
        errorView?.visibility = View.GONE
    }

    /// 加载异常布局
    protected fun showErrorView(tip: String, block: View.() -> UInt) {
        if (errorView != null) {
            errorView?.visibility = View.VISIBLE
            return
        }
        if (rootView != null) {
            val viewStub = rootView?.findViewById<ViewStub>(R.id.errorHoldView)
            if (viewStub != null) {
                errorView = viewStub.inflate()
                val loadErrorText = errorView?.findViewById<TextView>(R.id.tvErrorText)
                loadErrorText?.text = tip
                val loadErrorRootView = errorView?.findViewById<View>(R.id.errorRootView)
                loadErrorRootView?.setOnClickListener {
                    it?.block()
                }
            }
        }
    }

    /// 页面首次可见时调用一次该方法，在这里可以请求网络数据等
    open fun onNetwork() {

    }

    /// Bus通知
    @Subscribe(threadMode = ThreadMode.MAIN)
    open fun onMessageEvent(messageEvent: MessageEvent) {

    }
}