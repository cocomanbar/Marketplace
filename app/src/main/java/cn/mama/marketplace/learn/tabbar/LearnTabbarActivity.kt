package cn.mama.marketplace.learn.tabbar

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import cn.mama.marketplace.R
import cn.mama.marketplace.databinding.ActivityLearnGsonBinding
import cn.mama.marketplace.databinding.ActivityLearnTabbarBinding
import cn.mama.marketplace.ui.common.ui.BaseActivity

class LearnTabbarActivity : BaseActivity() {

    private lateinit var binding: ActivityLearnTabbarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLearnTabbarBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun setupViews() {
        super.setupViews()

        binding.tabBar.leftView.setOnClickListener {
            onBack()
        }
    }
}