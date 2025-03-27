package cn.mama.marketplace.utils

import android.app.Application
import android.content.Context
import android.view.View
import cn.mama.marketplace.R
import com.drake.statelayout.StateConfig
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

        // 缺省页
        StateConfig.apply {
            // 先将视图隐藏然后在800毫秒内渐变显示视图
            fun View.startAnimation() {
                animate().setDuration(0).alpha(0F).withEndAction {
                    animate().setDuration(800).alpha(1F)
                }
            }

            emptyLayout = R.layout.layout_state_empty
            loadingLayout = R.layout.layout_state_loading
            errorLayout = R.layout.layout_state_error

            // 全局重试Id
            setRetryIds(R.id.state_retry)

            onError {
                startAnimation()
            }
            onEmpty {
                startAnimation()
            }
            onContent {
                startAnimation()
            }
            onLoading {
                startAnimation()
            }
        }
    }

    // 初始化sdk-同意隐私协议后
    private fun initAfterPermission(context: Context, application: Application) {
        if (isInitAfterPrivacy) return
        isInitAfterPrivacy = true


    }
}