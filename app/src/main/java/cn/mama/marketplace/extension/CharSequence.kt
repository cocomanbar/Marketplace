package cn.mama.marketplace.extension

import android.widget.Toast
import cn.mama.marketplace.App

/**
 * 弹出Toast提示，[duration]显示消息的时间，LENGTH_SHORT、LENGTH_LONG
 */
fun CharSequence.showToast(duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(App.context, this, duration).show()
}
