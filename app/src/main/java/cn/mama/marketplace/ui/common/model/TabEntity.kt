package cn.mama.marketplace.ui.common.model

import com.flyco.tablayout.listener.CustomTabEntity

class TabEntity(
    private val title: String,
    private val selectionIcon: Int = 0,
    private val unSelectionIcon: Int = 0
) : CustomTabEntity {
    override fun getTabTitle(): String = title

    override fun getTabSelectedIcon(): Int = selectionIcon

    override fun getTabUnselectedIcon(): Int = unSelectionIcon
}