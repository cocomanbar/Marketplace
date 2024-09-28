package cn.mama.marketplace.ui

import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import cn.mama.marketplace.databinding.ActivitySplashBinding
import cn.mama.marketplace.ui.common.ui.BaseActivity
import com.gyf.immersionbar.ImmersionBar

class SplashActivity : BaseActivity() {

    private lateinit var binding: ActivitySplashBinding

    // 动画时间
    private val splashDuration = 3 * 1000L

    private val alphaAnimation by lazy {
        AlphaAnimation(0.1f, 1.0f).apply {
            duration = splashDuration
            fillAfter = true
        }
    }

    private val scaleAnimation by lazy {
        ScaleAnimation(
            1f,
            1.05f,
            1f,
            1.05f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        ).apply {
            duration = splashDuration
            fillAfter = true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun setupViews() {
        super.setupViews()

        binding.ivSplashBmg.startAnimation(scaleAnimation)
        binding.ivSlogan.startAnimation(alphaAnimation)
    }

    override fun setStatusBarBackground(statusBarColor: Int) {
        ImmersionBar.with(this)
            .transparentStatusBar()
            .init()
    }
}