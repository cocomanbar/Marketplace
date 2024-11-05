package cn.mama.marketplace

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import cn.mama.marketplace.utils.FrameworkUtil
import cn.mama.marketplace.utils.PreferenceUtil

class App : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
        context = this

        PreferenceUtil.instance.init(this)
        FrameworkUtil.initIfItNeeded(this, this)
    }
}