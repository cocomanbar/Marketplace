package cn.mama.marketplace.ui.common.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import cn.mama.marketplace.R
import com.flyco.tablayout.CommonTabLayout
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener


/**
 * Fragment基类，适用场景：页面含有ViewPager+TabLayout的界面
 */
abstract class BasePagerFragment : BaseFragment() {
    /**
     * 默认开启EventBus
     */
    override val eventBusOpenable: Boolean = true

    /**
     * 页面切换展示[viewPager]
     */
    protected var viewPager: ViewPager2? = null

    /**
     * 顶部滑动指示器[tabLayout]
     */
    protected var tabLayout: CommonTabLayout? = null

    /**
     * 代理监听[pageChangeCallback]
     */
    protected var pageChangeCallback: PageChangeCallback? = null

    /**
     * 基于[FragmentStateAdapter]的子类[VpAdapter]实现，保存页面状态
     */
    protected val adapter: VpAdapter by lazy {
        VpAdapter(requireActivity()).apply {
            addFragments(createFragments)
        }
    }

    /**
     * 固定一页展示限制
     */
    private var offscreenPageLimit: Int = 1

    /**
     *  滑动指示器的标题数组[createTitles]
     */
    abstract val createTitles: ArrayList<CustomTabEntity>

    /**
     * 滑动页面数组[createFragments]
     */
    abstract val createFragments: ArrayList<Fragment>

    /**
     * Fragment周期方法：创建完[view]的回调，在这里初始化[viewPager]以及[tabLayout]的框架
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
    }

    /**
     * 子类可重写，用于初始化部分view的地方
     */
    open fun setupViews() {
        initViewPager()
    }

    /**
     * 初始化页面框架的逻辑,[viewPager]以及[tabLayout]
     */
    protected fun initViewPager() {
        viewPager = rootView?.findViewById(R.id.viewPaper)
        tabLayout = rootView?.findViewById(R.id.tabLayout)

        viewPager?.offscreenPageLimit = offscreenPageLimit
        viewPager?.adapter = adapter

        tabLayout?.setTabData(createTitles)
        tabLayout?.setOnTabSelectListener(object : OnTabSelectListener {

            override fun onTabSelect(position: Int) {
                viewPager?.currentItem = position
            }

            override fun onTabReselect(position: Int) {

            }
        })

        pageChangeCallback = PageChangeCallback(tabLayout)
        viewPager?.registerOnPageChangeCallback(pageChangeCallback!!)
    }

    /**
     * 基于继承[ViewPager2.OnPageChangeCallback]的子类实现，监听[viewPager]滑动选择下标
     */
    class PageChangeCallback(private val tabLayout: CommonTabLayout?) :
        ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            tabLayout?.currentTab = position
        }
    }

    /**
     * 基于继承[FragmentStateAdapter]的子类实现，重写必须函数
     */
    class VpAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
        private val fragmentList = mutableListOf<Fragment>()

        fun addFragments(fragments: ArrayList<Fragment>) {
            fragmentList.clear()
            fragmentList.addAll(fragments)
        }

        override fun getItemCount() = fragmentList.count()

        override fun createFragment(position: Int) = fragmentList[position]
    }

    /**
     * 页面销毁函数
     */
    override fun onDestroy() {
        super.onDestroy()
        pageChangeCallback?.run { viewPager?.unregisterOnPageChangeCallback(this) }
        pageChangeCallback = null
    }
}