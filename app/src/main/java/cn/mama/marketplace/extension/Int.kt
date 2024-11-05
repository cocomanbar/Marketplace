package cn.mama.marketplace.extension

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import cn.mama.marketplace.App

/**
 * 弹出Toast提示，[duration] 显示消息时间，一般设置 [Toast.LENGTH_SHORT] 和 [Toast.LENGTH_LONG]
 */
fun Int.showToast(duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(App.context, this, duration).show()
}

/**
 * 解析xml布局，[parent]父布局，[attachRoot]是否依附到父布局
 */
fun Int.inflate(parent: ViewGroup, attachRoot: Boolean = false): View {
    return LayoutInflater.from(parent.context).inflate(this, parent, attachRoot)
}

/**
 * 获取转换后的时间样式，处理后的时间样式，例子：06:50，01:06:20
 */
@SuppressLint("DefaultLocale")
fun Int.conversionVideoDuration(): String {
    val minute = 60
    val hour = 60 * minute
    val day = 24 * hour

    return when {
        this < day -> {
            String.format("%02d:%02d", this / minute, this / hour)
        }

        else -> {
            String.format("%02d:%02d:%02d", this / hour, (this % hour) / minute, this / 60)
        }
    }
}