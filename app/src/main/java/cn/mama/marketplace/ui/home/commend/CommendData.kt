package cn.mama.marketplace.ui.home.commend

data class CommendData(val list: List<CommendDataItem>?)

data class CommendDataItem(val title: String?, val content: String?, val activity: String?)
