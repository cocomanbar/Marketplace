package cn.mama.marketplace.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.mama.marketplace.R
import cn.mama.marketplace.databinding.FragmentHomeContainerBinding
import cn.mama.marketplace.event.MessageEvent
import cn.mama.marketplace.event.PageRefreshEvent
import cn.mama.marketplace.event.PageSwitchEvent
import cn.mama.marketplace.extension.setOnClickListener
import cn.mama.marketplace.extension.showToast
import cn.mama.marketplace.ui.common.model.TabEntity
import cn.mama.marketplace.ui.common.ui.BasePagerFragment
import cn.mama.marketplace.ui.home.commend.CommendFragment
import cn.mama.marketplace.ui.home.daily.DailyFragment
import cn.mama.marketplace.ui.home.discovery.DiscoveryFragment
import cn.mama.marketplace.utils.ResourceUtil
import cn.mama.marketplace.utils.StatusBarUtil
import com.flyco.tablayout.listener.CustomTabEntity
import com.google.common.eventbus.EventBus

class HomeFragment : BasePagerFragment() {

    private lateinit var binding: FragmentHomeContainerBinding

    private val statusBarUtil: StatusBarUtil by lazy {
        StatusBarUtil(activity)
    }

    override val createTitles = ArrayList<CustomTabEntity>().apply {
        add(TabEntity("发现"))
        add(TabEntity("推荐"))
        add(TabEntity("日报"))
    }

    override val createFragments: Array<Fragment> =
        arrayOf(
            CommendFragment.newInstance(),
            DailyFragment.newInstance(),
            DiscoveryFragment.newInstance()
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        registerEventBus()

        statusBarUtil.setStatusBarColor(ResourceUtil.getColorInt(R.color.colorPrimary))
        statusBarUtil.makeDarkMode()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeContainerBinding.inflate(inflater, container, false)
        return onCreateViewLayoutIfNeeded(binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager?.currentItem = 1
        binding.titleBar.ivCalender.visibility = View.VISIBLE
        binding.titleBar.ivSearch.visibility = View.VISIBLE

        setOnClickListener(binding.titleBar.ivCalender, binding.titleBar.ivSearch) {
            when (this) {
                binding.titleBar.ivCalender -> {
                    R.string.currently_not_supported.showToast()
                }

                binding.titleBar.ivSearch -> {

                }
            }
        }
    }

    override fun onMessageEvent(messageEvent: MessageEvent) {
        super.onMessageEvent(messageEvent)

        if (messageEvent is PageRefreshEvent && this::class.java == messageEvent.activityClass) {
            when (viewPager?.currentItem) {
                0 -> EventBus().post(PageRefreshEvent(DiscoveryFragment::class.java))
                1 -> EventBus().post(PageRefreshEvent(CommendFragment::class.java))
                2 -> EventBus().post(PageRefreshEvent(DailyFragment::class.java))
            }
        } else if (messageEvent is PageSwitchEvent) {
            when (messageEvent.activityClass) {
                DiscoveryFragment::class.java -> viewPager?.currentItem = 0
                CommendFragment::class.java -> viewPager?.currentItem = 1
                DailyFragment::class.java -> viewPager?.currentItem = 2
            }
        }
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}