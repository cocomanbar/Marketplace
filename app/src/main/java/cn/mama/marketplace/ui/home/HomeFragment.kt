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
import com.flyco.tablayout.listener.CustomTabEntity
import com.google.common.eventbus.EventBus

class HomeFragment : BasePagerFragment() {

    private lateinit var binding: FragmentHomeContainerBinding


    override val createTitles: ArrayList<CustomTabEntity> = HomeTabEnum.homeTabEntities

    override val createFragments: ArrayList<Fragment> = HomeTabEnum.homeTabFragments

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
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

    @Suppress("UnstableApiUsage")
    override fun onMessageEvent(messageEvent: MessageEvent) {
        super.onMessageEvent(messageEvent)

        if (messageEvent is PageRefreshEvent && this::class.java == messageEvent.activityClass) {
            when (viewPager?.currentItem) {
                HomeTabEnum.DISCOVERY.index -> EventBus().post(PageRefreshEvent(HomeTabEnum.DISCOVERY.activityClass))
                HomeTabEnum.COMMEND.index -> EventBus().post(PageRefreshEvent(HomeTabEnum.COMMEND.activityClass))
                HomeTabEnum.DAILY.index -> EventBus().post(PageRefreshEvent(HomeTabEnum.DAILY.activityClass))
            }
        } else if (messageEvent is PageSwitchEvent) {
            when (messageEvent.activityClass) {
                HomeTabEnum.DISCOVERY.activityClass -> viewPager?.currentItem =
                    HomeTabEnum.DISCOVERY.index

                HomeTabEnum.COMMEND.activityClass -> viewPager?.currentItem =
                    HomeTabEnum.COMMEND.index

                HomeTabEnum.DAILY.activityClass -> viewPager?.currentItem = HomeTabEnum.DAILY.index
            }
        }
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}

interface HomeTabInterface {
    val index: Int
    val tabName: String
    val fragment: Fragment
    val activityClass: Class<*>
}

enum class HomeTabEnum : HomeTabInterface {
    DISCOVERY {
        override val index: Int
            get() = 0
        override val activityClass: Class<*>
            get() = DiscoveryFragment::class.java
        override val tabName: String
            get() = "发现"
        override val fragment: Fragment
            get() = DiscoveryFragment.newInstance()
    },
    COMMEND {
        override val index: Int
            get() = 1
        override val tabName: String
            get() = "推荐"
        override val activityClass: Class<*>
            get() = CommendFragment::class.java
        override val fragment: Fragment
            get() = CommendFragment.newInstance()
    },
    DAILY {
        override val index: Int
            get() = 2
        override val tabName: String
            get() = "日报"
        override val activityClass: Class<*>
            get() = DailyFragment::class.java
        override val fragment: Fragment
            get() = DailyFragment.newInstance()
    };

    companion object {
        val homeTabEntities: ArrayList<CustomTabEntity>
            get() = arrayListOf(
                TabEntity(DISCOVERY.tabName),
                TabEntity(COMMEND.tabName),
                TabEntity(DAILY.tabName),
            )
        val homeTabFragments: ArrayList<Fragment>
            get() = arrayListOf(
                DISCOVERY.fragment,
                COMMEND.fragment,
                DAILY.fragment,
            )
    }
}