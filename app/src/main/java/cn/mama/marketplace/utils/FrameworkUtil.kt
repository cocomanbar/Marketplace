package cn.mama.marketplace.utils

import android.app.Application
import android.content.Context
import com.hjq.toast.Toaster

object FrameworkUtil {

    private var isInitAfterPrivacy = false

    // app初始化入口
    fun initIfItNeeded(context: Context, application: Application) {
        initNoLimitPermission(context, application)
        initAfterPermission(context, application)
    }

    // 初始化sdk-同意隐私协议前
    private fun initNoLimitPermission(context: Context, application: Application) {

        // Toast
        Toaster.init(application)
    }

    // 初始化sdk-同意隐私协议后
    private fun initAfterPermission(context: Context, application: Application) {
        if (isInitAfterPrivacy) return
        isInitAfterPrivacy = true


    }
}