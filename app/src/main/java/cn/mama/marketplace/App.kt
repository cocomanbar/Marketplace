package cn.mama.marketplace

import android.app.Application
import android.content.Context
import cn.mama.marketplace.utils.SdkManager

class App : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()

        SdkManager.initIfItNeeded(this, this)
    }
}