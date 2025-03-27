package cn.mama.marketplace.learn.smartRefreshLayout

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import cn.mama.marketplace.R
import cn.mama.marketplace.databinding.ActivityLearnSmartRefreshLayoutBinding
import cn.mama.marketplace.ui.common.ui.BaseActivity

class LearnSmartRefreshLayoutActivity : BaseActivity() {

    private lateinit var binding: ActivityLearnSmartRefreshLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLearnSmartRefreshLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun setupViews() {
        super.setupViews()

    }
}