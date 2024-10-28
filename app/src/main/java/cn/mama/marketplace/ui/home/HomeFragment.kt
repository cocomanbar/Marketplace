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
import cn.mama.marketplace.ui.common.model.TabEntity
import cn.mama.marketplace.ui.common.ui.BaseFragment
import cn.mama.marketplace.ui.common.ui.BasePagerFragment
import cn.mama.marketplace.ui.home.commend.CommendFragment
import cn.mama.marketplace.ui.home.daily.DailyFragment
import cn.mama.marketplace.ui.home.discovery.DiscoveryFragment
import com.flyco.tablayout.listener.CustomTabEntity
import com.google.common.eventbus.EventBus

class HomeFragment : BasePagerFragment() {

    private lateinit var binding: FragmentHomeContainerBinding

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