package cn.mama.marketplace.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.window.OnBackInvokedDispatcher
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.work.WorkInfo
import androidx.work.WorkManager
import cn.mama.marketplace.R
import cn.mama.marketplace.databinding.ActivityMainBinding
import cn.mama.marketplace.event.PageRefreshEvent
import cn.mama.marketplace.extension.setOnClickListener
import cn.mama.marketplace.extension.showToast
import cn.mama.marketplace.ui.common.ui.BaseActivity
import cn.mama.marketplace.ui.community.CommunityFragment
import cn.mama.marketplace.ui.home.HomeFragment
import cn.mama.marketplace.ui.login.LoginActivity
import cn.mama.marketplace.ui.mine.MineFragment
import cn.mama.marketplace.ui.notification.NotificationFragment
import cn.mama.marketplace.utils.ActivityCollector
import cn.mama.marketplace.utils.ProjectUtil
import cn.mama.marketplace.utils.ResourceUtil
import cn.mama.marketplace.worker.DialogAppraiseTipsWorker
import com.google.common.eventbus.EventBus

class MainActivity : BaseActivity() {

    enum class TabEnum {
        HOME, COMMUNITY, NOTIFICATION, MINE,
    }

    private lateinit var binding: ActivityMainBinding

    private var backPressTime: Long = 0L

    private var homeFragment: HomeFragment? = null
    private var communityFragment: CommunityFragment? = null
    private var notificationFragment: NotificationFragment? = null
    private var mineFragment: MineFragment? = null

    private val fragmentManager: FragmentManager by lazy { supportFragmentManager }

    override val onBackPressable: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun setupViews() {
        super.setupViews()
        observe()
        setTabSelection(TabEnum.HOME)

        setOnClickListener(
            binding.tabBar.homeTab,
            binding.tabBar.communityTab,
            binding.tabBar.releaseTab,
            binding.tabBar.notificationTab,
            binding.tabBar.mineTab,
        ) {
            when (this) {
                binding.tabBar.homeTab -> {
                    setTabSelection(TabEnum.HOME)
                }

                binding.tabBar.communityTab -> {
                    setTabSelection(TabEnum.COMMUNITY)
                }

                binding.tabBar.releaseTab -> {
                    LoginActivity.start(this@MainActivity)
                }

                binding.tabBar.notificationTab -> {
                    setTabSelection(TabEnum.NOTIFICATION)
                }

                binding.tabBar.mineTab -> {
                    setTabSelection(TabEnum.MINE)
                }
            }
        }
    }

    @Suppress("UnstableApiUsage")
    private fun notificationUiRefresh(tabEnum: TabEnum) {
        when (tabEnum) {
            TabEnum.HOME -> {
                if (binding.tabBar.homeTab.isSelected) EventBus().post(PageRefreshEvent(HomeFragment::class.java))
            }

            TabEnum.COMMUNITY -> {
                if (binding.tabBar.homeTab.isSelected) EventBus().post(
                    PageRefreshEvent(
                        CommunityFragment::class.java
                    )
                )
            }

            TabEnum.NOTIFICATION -> {
                if (binding.tabBar.homeTab.isSelected) EventBus().post(
                    PageRefreshEvent(
                        NotificationFragment::class.java
                    )
                )
            }

            TabEnum.MINE -> {
                if (binding.tabBar.homeTab.isSelected) EventBus().post(PageRefreshEvent(MineFragment::class.java))
            }
        }
    }

    @SuppressLint("CommitTransaction")
    private fun setTabSelection(tabEnum: TabEnum) {
        notificationUiRefresh(tabEnum)
        clearAllSelected()

        fragmentManager.beginTransaction().apply {
            hideFragments(this)

            when (tabEnum) {
                TabEnum.HOME -> {
                    binding.tabBar.homeTab.isSelected = true
                    if (homeFragment == null) {
                        homeFragment = HomeFragment.newInstance()
                        add(R.id.homeActivityFragContainer, homeFragment!!)
                    } else {
                        show(homeFragment!!)
                    }
                }

                TabEnum.COMMUNITY -> {
                    binding.tabBar.communityTab.isSelected = true
                    if (communityFragment == null) {
                        communityFragment = CommunityFragment.newInstance()
                        add(R.id.homeActivityFragContainer, communityFragment!!)
                    } else {
                        show(communityFragment!!)
                    }
                }

                TabEnum.NOTIFICATION -> {
                    binding.tabBar.notificationTab.isSelected = true
                    if (notificationFragment == null) {
                        notificationFragment = NotificationFragment.newInstance()
                        add(R.id.homeActivityFragContainer, notificationFragment!!)
                    } else {
                        show(notificationFragment!!)
                    }
                }

                TabEnum.MINE -> {
                    binding.tabBar.mineTab.isSelected = true
                    if (mineFragment == null) {
                        mineFragment = MineFragment.newInstance()
                        add(R.id.homeActivityFragContainer, mineFragment!!)
                    } else {
                        show(mineFragment!!)
                    }
                }
            }
        }.commitAllowingStateLoss()
    }

    private fun clearAllSelected() {
        if (binding.tabBar.homeTab.isSelected) {
            binding.tabBar.homeTab.isSelected = false
        }
        if (binding.tabBar.communityTab.isSelected) {
            binding.tabBar.communityTab.isSelected = false
        }
        if (binding.tabBar.notificationTab.isSelected) {
            binding.tabBar.notificationTab.isSelected = false
        }
        if (binding.tabBar.mineTab.isSelected) {
            binding.tabBar.mineTab.isSelected = false
        }
    }

    private fun hideFragments(transaction: FragmentTransaction) {
        transaction.run {
            if (homeFragment != null) hide(homeFragment!!)
            if (communityFragment != null) hide(communityFragment!!)
            if (notificationFragment != null) hide(notificationFragment!!)
            if (mineFragment != null) hide(mineFragment!!)
        }
    }

    private fun observe() {
        WorkManager.getInstance(this)
            .enqueue(DialogAppraiseTipsWorker.showDialogWorkRequest)

        WorkManager.getInstance(this)
            .getWorkInfoByIdLiveData(DialogAppraiseTipsWorker.showDialogWorkRequest.id)
            .observe(this) { workInfo ->
                if (workInfo.state == WorkInfo.State.SUCCEEDED) {
                    WorkManager.getInstance(this).cancelAllWork()
                } else if (workInfo.state == WorkInfo.State.RUNNING) {
                    if (isActive) {
                        DialogAppraiseTipsWorker.showDialog(this)
                        WorkManager.getInstance(this).cancelAllWork()
                    }
                }
            }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onHandleOnBackPressed() {
        super.onHandleOnBackPressed()

        val now = System.currentTimeMillis()
        if (now - backPressTime > 2000) {
            String.format(ResourceUtil.getString(R.string.press_again_to_exit), ProjectUtil.appName)
                .showToast()
            backPressTime = now
        } else {
            ActivityCollector.existApp()
        }
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }
}