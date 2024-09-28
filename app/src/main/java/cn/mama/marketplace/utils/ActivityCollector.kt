package cn.mama.marketplace.utils

import android.app.Activity
import java.lang.ref.WeakReference
import java.util.Stack

object ActivityCollector {

    // 活动栈
    private val activities = Stack<WeakReference<Activity>>()

    // 将Activity压入Application栈
    fun pushTask(task: WeakReference<Activity>?) {
        activities.push(task)
    }

    // 将传入的Activity对象从栈中移除
    fun removeTask(task: WeakReference<Activity>?) {
        activities.remove(task)
    }

    // 查询数量
    fun count(): Int {
        return activities.count()
    }
}