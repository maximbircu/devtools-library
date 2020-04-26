package com.maximbircu.devtools.android.presentation.tool

import android.content.Context
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.maximbircu.devtools.android.R
import com.maximbircu.devtools.android.extensions.hide
import com.maximbircu.devtools.android.extensions.show
import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.presentation.tool.help.DevToolHelpDialogPresenter
import com.maximbircu.devtools.common.presentation.tool.help.DevToolHelpDialogView
import kotlinx.android.synthetic.main.layout_dev_tool_help_dialog.criticalUpdate
import kotlinx.android.synthetic.main.layout_dev_tool_help_dialog.currentValue
import kotlinx.android.synthetic.main.layout_dev_tool_help_dialog.defaultValue
import kotlinx.android.synthetic.main.layout_dev_tool_help_dialog.description
import kotlinx.android.synthetic.main.layout_dev_tool_help_dialog.toolKey

class DevToolHelpDialog(
    context: Context,
    private val tool: DevTool<*>
) : DevToolHelpDialogView {
    private val presenter = DevToolHelpDialogPresenter.create(this)
    private val dialog = MaterialAlertDialogBuilder(context)
        .setPositiveButton(android.R.string.ok, null)
        .setTitle(tool.title)
        .setView(R.layout.layout_dev_tool_help_dialog)
        .create()

    fun show() {
        dialog.show()
        presenter.onToolBind(tool)
    }

    override fun setDescription(description: String) {
        dialog.description.text = description
    }

    override fun hideDescription() {
        dialog.description.hide()
    }

    override fun setKey(toolKey: String) {
        dialog.toolKey.value = toolKey
    }

    override fun setCurrentConfigValue(currentConfigValue: String) {
        dialog.currentValue.value = currentConfigValue
    }

    override fun setDefaultConfigValue(defaultConfigValue: String) {
        dialog.defaultValue.value = defaultConfigValue
    }

    override fun showCriticalUpdateLabel() {
        dialog.criticalUpdate.show()
    }

    override fun hideCriticalUpdateLabel() {
        dialog.criticalUpdate.hide()
    }
}
