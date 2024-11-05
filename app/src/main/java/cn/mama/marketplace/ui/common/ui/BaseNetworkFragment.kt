package cn.mama.marketplace.ui.common.ui

import android.view.View
import android.view.ViewStub
import android.widget.TextView
import cn.mama.marketplace.R
import cn.mama.marketplace.utils.logD

interface BaseNetworkFragmentLifecycle {
    fun startLoading()

    fun loadFinished()

    fun loadFailed(msg: String?)
}

/**
 * 项目里所有涉及到请求网络数据展示页面的Fragment基类，实现网络状态基础层
 */
open class BaseNetworkFragment : BaseFragment(), BaseNetworkFragmentLifecycle {

    /**
     * 是否已经加载过数据
     */
    private var mHasLoadedData: Boolean = false

    /**
     * Fragment 加载等待的布局
     */
    protected var loadingView: View? = null

    /**
     * Fragment 加载失败的布局
     */
    protected var errorView: View? = null


    /**
     * Fragment周期方法：当Fragment开始与用户交互时调用
     */
    override fun onResume() {
        super.onResume()
        //当Fragment在屏幕上可见并且没有加载过数据时调用
        if (!mHasLoadedData) {
            onNetwork()
            logD(TAG, "BaseFragment-->onNetwork")
            mHasLoadedData = true
        }
    }

    /**
     * 在Fragment基类中获取通用的控件，会将传入的[view]实例，再原封不动传回。为了赋值[rootView]以及初始化[loadingView]组件
     */
    override fun onCreateViewLayoutIfNeeded(view: View): View {
        loadingView = view.findViewById(R.id.loading)
        return super.onCreateViewLayoutIfNeeded(view)
    }

    /**
     * 网络数据开始加载，将显示加载布局
     */
    override fun startLoading() {
        showLoadingView()
        hideErrorView()
    }

    /**
     * 网络数据加载完成，将加载控件隐藏
     */
    override fun loadFinished() {
        hideLoadingView()
    }

    /**
     * 子类在处理网络数据时，遇到加载请求失败时的处理行为。
     *      1.隐藏loading视图
     *      2.通过手动调用 -showErrorView方法展示错误提示页
     */
    override fun loadFailed(msg: String?) {
        hideLoadingView()
    }

    /**
     * 隐藏加载布局
     */
    protected fun hideLoadingView() {
        loadingView?.visibility = View.GONE
    }

    /**
     * 显示加载布局
     */
    protected fun showLoadingView() {
        loadingView?.visibility = View.VISIBLE
    }

    /**
     * 隐藏异常布局
     */
    protected fun hideErrorView() {
        errorView?.visibility = View.GONE
    }

    /**
     * 加载异常布局，子类调用，传入[message]错误提示信息,以及[block]点击回调
     */
    protected fun showErrorView(message: String, block: View.() -> UInt) {
        if (errorView != null) {
            errorView?.visibility = View.VISIBLE
            return
        }
        if (rootView != null) {
            val viewStub = rootView?.findViewById<ViewStub>(R.id.errorHoldView)
            if (viewStub != null) {
                errorView = viewStub.inflate()
                val loadErrorText = errorView?.findViewById<TextView>(R.id.tvErrorText)
                loadErrorText?.text = message
                val loadErrorRootView = errorView?.findViewById<View>(R.id.errorRootView)
                loadErrorRootView?.setOnClickListener {
                    it?.block()
                }
            }
        }
    }

    /**
     *  页面首次可见时调用一次该方法，在这里可以请求网络数据等
     */
    open fun onNetwork() {

    }
}