package cn.mama.marketplace.utils

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
import java.util.concurrent.TimeUnit

class DialogAppraiseTipsWorker(val context: Context, parameters: WorkerParameters) :
    Worker(context, parameters) {

    companion object {

        const val TAG = "DialogAppraiseTipsWorker"

        val showDialogWorkRequest = OneTimeWorkRequest.Builder(DialogAppraiseTipsWorker::class.java)
            .addTag(TAG)
            .setInitialDelay(duration = 1, TimeUnit.MINUTES)
            .setBackoffCriteria(BackoffPolicy.LINEAR, backoffDelay = 5, TimeUnit.SECONDS)
            .build()

        var isNeedShowDialog: Boolean
            get() = MMKVUtil.instance.getBoolean("is_need_show_dialog", defaultValue = true)
            set(value) {
                MMKVUtil.instance.put("is_need_show_dialog", value)
            }

        private var dialog: AlertDialog? = null

        @SuppressLint("InflateParams")
        fun showDialog(context: Context) {
            if (!isNeedShowDialog) return

            val dialogView =
                LayoutInflater.from(context).inflate(R.layout.layout_appraise_dialog, null)
                    .apply {
                        findViewById<TextView>(R.id.tvEncourageMessage).text = String.format(
                            GlobalUtil.getResourceString(R.string.encourage_message),
                            GlobalUtil.appName
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

    override fun doWork(): Result {
        return if (isNeedShowDialog) {
            Result.retry()
        } else {
            Result.success()
        }
    }
}