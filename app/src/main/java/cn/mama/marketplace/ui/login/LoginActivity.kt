package cn.mama.marketplace.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import cn.mama.marketplace.R
import cn.mama.marketplace.databinding.ActivityLoginBinding
import cn.mama.marketplace.ui.common.ui.BaseActivity

class LoginActivity : BaseActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    companion object {

        fun start(context: Context) {
            context.startActivity(Intent(context, LoginActivity::class.java))
        }
    }
}