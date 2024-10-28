package cn.mama.marketplace.event

/** 通知Tab切换页面，从[activityClass]页面发出来 */
class PageSwitchEvent(val activityClass: Class<*>? = null) : MessageEvent()