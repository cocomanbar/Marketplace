package cn.mama.marketplace.event

/** 通知 [activityClass] 刷新界面消息 */
class PageRefreshEvent(private val activityClass: Class<*>? = null) : MessageEvent()