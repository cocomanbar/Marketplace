package cn.mama.marketplace.utils

import com.tencent.mmkv.MMKV

object LocalStorage {

    private const val ANDROID_ID = "AndroidId"
    private const val USER_AGREE = "UserAgree"

    private var KV: MMKV? = null
        get() {
            if (field == null) {
                runCatching {
                    field = MMKV.defaultMMKV(MMKV.MULTI_PROCESS_MODE, null)
                }
            }
            return field
        }

    @JvmStatic
    var androidId: String? = null
        get() {
            if (field == null) {
                field = KV?.getString(ANDROID_ID, null)
            }
            return field
        }
        set(value) {
            KV?.putString(ANDROID_ID, value)
            field = value
        }

    @JvmStatic
    var userAgree: Boolean = false
        get() {
            if (!field) {
                field = (KV?.getBoolean(USER_AGREE, false)) ?: false
            }
            return field
        }
        set(value) {
            KV?.putBoolean(USER_AGREE, value)
            field = value
        }
}