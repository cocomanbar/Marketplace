package cn.mama.marketplace.learn

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import cn.mama.marketplace.databinding.ZLearnRadioButtonBinding
import cn.mama.marketplace.ui.common.ui.BaseActivity
import com.hjq.toast.Toaster

class LRadioButtonActivity : BaseActivity() {

    private lateinit var binding: ZLearnRadioButtonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ZLearnRadioButtonBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun setupViews() {
        super.setupViews()

        binding.radioGroup.setOnCheckedChangeListener { _, checkId ->
            if (binding.radioMale.id == checkId) {
                Toaster.show("哈哈哈：" + binding.radioMale.text)
            } else if (binding.radioFemale.id == checkId) {
                Toaster.show("哈哈哈：" + binding.radioFemale.text)
            }
        }

        binding.button1.setOnClickListener {
            val button = findViewById<RadioButton>(binding.radioGroup.checkedRadioButtonId)
            Toaster.show("哈哈哈：" + button.text)
        }

        binding.button2.setOnClickListener {
            var text = ""
            if (binding.checkbox1.isChecked) {
                text += binding.checkbox1.text
                text += " "
            }
            if (binding.checkbox2.isChecked) {
                text += binding.checkbox2.text
                text += " "
            }
            if (binding.checkbox3.isChecked) {
                text += binding.checkbox3.text
                text += " "
            }
            if (binding.checkbox4.isChecked) {
                text += binding.checkbox4.text
                text += " "
            }
            Toaster.show("哈哈哈：$text")
        }

        binding.toggleButton.setOnClickListener {
            Toaster.show("哈哈哈：")
        }

        binding.switch1.setOnClickListener {
            Toaster.show("哈哈哈：")
        }
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, LRadioButtonActivity::class.java))
        }
    }
}