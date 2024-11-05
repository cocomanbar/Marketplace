package cn.mama.marketplace.utils

import android.content.Context
import android.os.Parcelable
import com.tencent.mmkv.MMKV


class PreferenceUtil private constructor() {

    // 懒加载
    // set保护
    lateinit var mmkv: MMKV private set

    companion object {
        val instance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { PreferenceUtil() }
    }

    fun init(context: Context) {
        MMKV.initialize(context)
        mmkv = MMKV.defaultMMKV(MMKV.MULTI_PROCESS_MODE, null)
    }

    fun remove(key: String) {
        mmkv.remove(key)
    }

    fun clearAll() = mmkv.clearAll()

    fun count(): Long = mmkv.count()

    /// 存储基本数据类型
    fun put(key: String, value: Any?): Boolean {
        return when (value) {
            is String -> mmkv.encode(key, value)
            is Float -> mmkv.encode(key, value)
            is Boolean -> mmkv.encode(key, value)
            is Int -> mmkv.encode(key, value)
            is Long -> mmkv.encode(key, value)
            is Double -> mmkv.encode(key, value)
            is ByteArray -> mmkv.encode(key, value)
            else -> false
        }
    }

    /// 存储遵守`Parcelable`对象数据
    fun <V : Parcelable> put(key: String, value: V?): Boolean {
        if (value == null) return false
        return mmkv.encode(key, value)
    }

    /// 存储 Set<String> 值数据
    fun put(key: String, value: Set<String>?): Boolean {
        if (value == null) return false
        return mmkv.encode(key, value)
    }

    fun getInt(key: String, defaultValue: Int = 0): Int {
        return mmkv.decodeInt(key, defaultValue)
    }

    fun getDouble(key: String, defaultValue: Double = 0.0): Double {
        return mmkv.decodeDouble(key, defaultValue)
    }

    fun getLong(key: String, defaultValue: Long = 0L): Long {
        return mmkv.decodeLong(key, defaultValue)
    }

    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return mmkv.decodeBool(key, defaultValue)
    }

    fun getFloat(key: String, defaultValue: Float = 0F): Float {
        return mmkv.decodeFloat(key, defaultValue)
    }

    fun getString(key: String, defaultValue: String? = null): String? {
        return mmkv.decodeString(key, defaultValue)
    }

    fun getByteArray(key: String, defaultValue: ByteArray? = null): ByteArray? {
        return mmkv.decodeBytes(key, defaultValue)
    }

    inline fun <reified T : Parcelable> getParcelable(key: String, defaultValue: T?): T? {
        return mmkv.decodeParcelable(key, T::class.java, defaultValue)
    }

    fun getStringSet(key: String, defaultValue: Set<String>?): Set<String>? {
        return mmkv.decodeStringSet(key, defaultValue)
    }
}