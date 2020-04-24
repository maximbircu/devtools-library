package com.maximbircu.devtools.android.presentation.tool

import android.view.MenuItem
import android.widget.PopupMenu.OnMenuItemClickListener
import com.maximbircu.devtools.android.R.id
import com.maximbircu.devtools.common.presentation.tool.DevToolContextMenuPresenter

class DevToolContextMenuClickListener(
    private val presenter: DevToolContextMenuPresenter
) : OnMenuItemClickListener {
    override fun onMenuItemClick(item: MenuItem): Boolean {
        when (item.itemId) {
            id.help -> presenter.onHelp()
            id.select_default_value -> presenter.onSelectDefaultValue()
            id.reset_selection -> presenter.onResetSelection()
            else -> return false
        }
        return true
    }
}
