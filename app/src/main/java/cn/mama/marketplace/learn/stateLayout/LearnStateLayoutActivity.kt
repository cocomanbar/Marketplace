package cn.mama.marketplace.learn.stateLayout

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import cn.mama.marketplace.R
import cn.mama.marketplace.databinding.ActivityLearnStateLayoutBinding
import cn.mama.marketplace.databinding.ActivityLearnTabbarBinding
import cn.mama.marketplace.ui.common.ui.BaseActivity

class LearnStateLayoutActivity : BaseActivity() {

    private lateinit var binding: ActivityLearnStateLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLearnStateLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun setupViews() {
        super.setupViews()

        binding.tabBar.leftView.setOnClickListener {
            onBack()
        }

        binding.tabBar.rightView.setOnClickListener {
            showPopupMenu(it)
        }
    }

    private fun showPopupMenu(view: View) {
        PopupMenu(this, view).apply {
            menuInflater.inflate(R.menu.menu_state_layout, menu)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.loading -> {
                        binding.stateLayout.showLoading()
                    }

                    R.id.error -> {
                        binding.stateLayout.showError()
                    }

                    R.id.empty -> {
                        binding.stateLayout.showEmpty()
                    }

                    R.id.normal -> {
                        binding.stateLayout.showContent()
                    }

                    R.id.custom -> {
                        // 组件无入口，通过页面自定义，修改例如 layout_state_custom_empty
                    }
                }
                true
            }
        }.show()
    }
}