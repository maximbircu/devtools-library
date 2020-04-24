package com.maximbircu.devtools.android.presentation.tools.group

import android.view.MenuItem
import android.widget.PopupMenu.OnMenuItemClickListener
import com.maximbircu.devtools.android.R.id
import com.maximbircu.devtools.common.presentation.tools.group.GroupToolContextMenuPresenter

class GroupToolContextMenuClickListener(
    private val presenter: GroupToolContextMenuPresenter
) : OnMenuItemClickListener {
    override fun onMenuItemClick(item: MenuItem): Boolean {
        when (item.itemId) {
            id.enable_all -> presenter.onEnableAll()
            id.disable_all -> presenter.onDisableAll()
            id.set_all_to_default -> presenter.onSetAllToDefault()
            id.reset_all_changes -> presenter.onResetAllChanges()
            else -> return false
        }
        return true
    }
}
