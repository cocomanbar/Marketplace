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


/// Fragment基类，适用场景：页面含有ViewPager+TabLayout的界面
abstract class BasePagerFragment : BaseFragment() {

    protected var viewPager: ViewPager2? = null

    protected var tabLayout: CommonTabLayout? = null

    protected var pageChangeCallback: PageChangeCallback? = null

    protected val adapter: VpAdapter by lazy {
        VpAdapter(requireActivity()).apply {
            addFragments(createFragments)
        }
    }

    protected var offscreenPageLimit: Int = 1

    abstract val createTitles: ArrayList<CustomTabEntity>

    abstract val createFragments: Array<Fragment>
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
    }

    open fun setupViews() {
        initViewPager()
    }

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

    class PageChangeCallback(private val tabLayout: CommonTabLayout?) :
        ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            tabLayout?.currentTab = position
        }
    }

    class VpAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
        private val fragmentList = mutableListOf<Fragment>()

        fun addFragments(fragments: Array<Fragment>) {
            fragmentList.clear()
            fragmentList.addAll(fragments)
        }

        override fun getItemCount() = fragmentList.count()

        override fun createFragment(position: Int) = fragmentList[position]
    }

    override fun onDestroy() {
        super.onDestroy()
        pageChangeCallback?.run { viewPager?.unregisterOnPageChangeCallback(this) }
        pageChangeCallback = null
    }
}