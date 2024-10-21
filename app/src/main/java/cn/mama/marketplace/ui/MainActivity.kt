package cn.mama.marketplace.ui

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.work.WorkManager
import cn.mama.marketplace.databinding.ActivityMainBinding
import cn.mama.marketplace.ui.common.ui.BaseActivity
import cn.mama.marketplace.ui.community.CommunityFragment
import cn.mama.marketplace.ui.home.HomeFragment
import cn.mama.marketplace.ui.mine.MineFragment
import cn.mama.marketplace.ui.notification.NotificationFragment

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    private var backPressTime: Long = 0L

    private var homeFragment: HomeFragment? = null
    private var communityFragment: CommunityFragment? = null
    private var notificationFragment: NotificationFragment? = null
    private var mineFragment: MineFragment? = null

    private val fragmentManager: FragmentManager by lazy { supportFragmentManager }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun setupViews() {
        super.setupViews()

    }

    private fun observe() {
        WorkManager.getInstance(this)
    }
}