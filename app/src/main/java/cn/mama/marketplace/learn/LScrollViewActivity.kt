package cn.mama.marketplace.learn

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import cn.mama.marketplace.databinding.ZLearnScrollviewBinding
import cn.mama.marketplace.ui.common.ui.BaseActivity

class LScrollViewActivity : BaseActivity() {

    private lateinit var binding: ZLearnScrollviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ZLearnScrollviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun setupViews() {
        super.setupViews()

        // 滚动到顶部
        binding.button1.setOnClickListener {
            binding.scrollView.fullScroll(View.FOCUS_UP)
        }

        // 滚动到底部
        binding.button2.setOnClickListener {
            binding.scrollView.fullScroll(View.FOCUS_DOWN)
        }

        // textView
        val sb = StringBuilder()
        for (i in 0..100) {
            sb.append("这是一句话：$i\n")
        }
        binding.textView.text = sb.toString()

    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, LScrollViewActivity::class.java))
        }
    }
}