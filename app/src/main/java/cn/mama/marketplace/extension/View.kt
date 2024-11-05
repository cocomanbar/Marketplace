package cn.mama.marketplace.extension

import android.view.View

/** 批量视图 [v] 设置点击事件 [block]  */
fun setOnClickListener(vararg v: View?, block: View.() -> Unit) {
    val listener = View.OnClickListener { it.block() }
    v.forEach { it?.setOnClickListener(listener) }
}
