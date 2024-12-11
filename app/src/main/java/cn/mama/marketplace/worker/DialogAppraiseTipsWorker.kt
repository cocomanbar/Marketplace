package cn.mama.marketplace.worker

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.work.BackoffPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.Worker
import androidx.work.WorkerParameters
import cn.mama.marketplace.R
import cn.mama.marketplace.utils.PreferenceUtil
import cn.mama.marketplace.utils.ProjectUtil
import cn.mama.marketplace.utils.ResourceUtil
import cn.mama.marketplace.utils.dp2px
import cn.mama.marketplace.utils.screenWidth
import java.util.concurrent.TimeUnit

/*
* WorkManager
*   适合用于持久性工作的推荐解决方案。如果工作始终要通过应用重启和系统重新启动来调度，便是持久性的工作。
*   适用于需要可靠运行的工作，即使用户导航离开屏幕、退出应用或重启设备也不影响工作的执行
*   由于大多数后台处理操作都是通过持久性工作完成的，因此 WorkManager 是适用于后台处理操作的主要推荐 API
*
*       向后端服务发送日志或分析数据。
*       定期将应用数据与服务器同步。
*
* a.立即，一次性，OneTimeWorkRequest 和 Worker。如需处理加急工作，请对 OneTimeWorkRequest 调用 setExpedited()
* b.长期运行，一次性或定期，任意 WorkRequest 或 Worker。在工作器中调用 setForeground() 来处理通知
* c.可延期，一次性或定期，PeriodicWorkRequest 和 Worker
*
*   WorkManager 的使用简单描述3个步骤
*       定义工作，创建Worker实现类
*       创建工作请求，WorkRequest
*       将 WorkRequest 提交给系统
*
* DialogAppraiseTipsWorker是一个后台执行业务的worker，继承worker扩展属于自己业务的worker，重写doWork
*
* 还有一种worker：CoroutineWorker
*       执行异步任务，即使用户关闭应用也可以确保任务完成
* */
class DialogAppraiseTipsWorker(val context: Context, parameters: WorkerParameters) :
    Worker(context, parameters) {

    companion object {

        const val TAG = "DialogAppraiseTipsWorker"

        val showDialogWorkRequest = OneTimeWorkRequest.Builder(DialogAppraiseTipsWorker::class.java)
            .addTag(TAG)
            .setInitialDelay(duration = 10, TimeUnit.SECONDS)
            .setBackoffCriteria(BackoffPolicy.LINEAR, backoffDelay = 5, TimeUnit.SECONDS)
            .build()

        var isNeedShowDialog: Boolean
            get() = PreferenceUtil.instance.getBoolean("is_need_show_dialog", defaultValue = true)
            set(value) {
                PreferenceUtil.instance.put("is_need_show_dialog", value)
            }

        private var dialog: AlertDialog? = null

        @SuppressLint("InflateParams")
        fun showDialog(context: Context) {
            if (!isNeedShowDialog) return

            val dialogView =
                LayoutInflater.from(context).inflate(R.layout.layout_appraise_dialog, null)
                    .apply {
                        findViewById<TextView>(R.id.tvEncourageMessage).text = String.format(
                            ResourceUtil.getString(R.string.encourage_message),
                            ProjectUtil.appName
                        )
                        findViewById<TextView>(R.id.tvPositiveButton).setOnClickListener {
                            dialog?.dismiss()
                        }
                        findViewById<TextView>(R.id.tvNegativeButton).setOnClickListener {
                            dialog?.dismiss()
                        }
                    }
            dialog = AlertDialog.Builder(context, R.style.MarketplaceAlertDialogStyle)
                .setCancelable(false).setView(dialogView).create()
            dialog?.show()
            dialog?.window?.attributes = dialog?.window?.attributes?.apply {
                width = screenWidth - (dp2px(20f) * 2)
                height = ViewGroup.LayoutParams.WRAP_CONTENT
            }
            isNeedShowDialog = false
        }
    }

    /*
    * 当您使用 Worker 的时候，WorkManager 会在后台线程中自动调用 Worker.doWork()。
    * doWork() 返回的 Result 会告知 WorkManager 服务是否成功，如果失败则告知是否需要重试。
    *
    * 这个worker的执行顺序是
    * */
    override fun doWork(): Result {
        return if (isNeedShowDialog) {
            Result.retry()
        } else {
            Result.success()
        }
    }
}