package cn.mama.marketplace

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import cn.mama.marketplace.utils.SdkManager

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

        SdkManager.initIfItNeeded(this, this)
    }
}