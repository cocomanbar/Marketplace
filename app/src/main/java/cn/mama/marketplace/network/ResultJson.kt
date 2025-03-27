package cn.mama.marketplace.network

data class ResultJson<T>(
    val code: Int = Int.MAX_VALUE,
    val msg: String? = null,
    val data: T? = null
)
