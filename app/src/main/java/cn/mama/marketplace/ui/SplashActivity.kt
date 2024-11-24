package cn.mama.marketplace.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import cn.mama.marketplace.databinding.ActivitySplashBinding
import cn.mama.marketplace.ui.common.ui.BaseActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity() {

    private lateinit var binding: ActivitySplashBinding

    // 动画时间
    private val splashDuration = 1 * 1000L

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
        entryMainActivity()

        statusBarUtil.makeTransparentStatusBar()

        binding.ivSplashBmg.startAnimation(scaleAnimation)
        binding.ivSlogan.startAnimation(alphaAnimation)
        binding.tvDescriptionCN.startAnimation(alphaAnimation)
        binding.tvDescriptionUS.startAnimation(alphaAnimation)
    }

    private fun entryMainActivity() {
        scaleAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }
        })
    }
}