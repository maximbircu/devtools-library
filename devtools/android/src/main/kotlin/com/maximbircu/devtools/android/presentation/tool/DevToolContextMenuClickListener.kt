package com.maximbircu.devtools.android.presentation.tool

import android.view.MenuItem
import android.widget.PopupMenu.OnMenuItemClickListener
import com.maximbircu.devtools.android.R
import com.maximbircu.devtools.common.presentation.tool.DevToolContextMenuPresenter

internal class DevToolContextMenuClickListener(
    private val presenter: DevToolContextMenuPresenter
) : OnMenuItemClickListener {
    override fun onMenuItemClick(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.help -> presenter.onHelp()
            R.id.select_default_value -> presenter.onSelectDefaultValue()
            R.id.reset_changes -> presenter.onResetChanges()
            else -> throw IllegalArgumentException("$item not supported!")
        }
        return true
    }
}
