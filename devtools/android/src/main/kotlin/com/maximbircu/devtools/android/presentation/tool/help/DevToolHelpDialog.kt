package com.maximbircu.devtools.android.presentation.tool.help

import android.content.Context
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.maximbircu.devtools.android.R
import com.maximbircu.devtools.android.databinding.LayoutDevToolHelpDialogBinding
import com.maximbircu.devtools.android.extensions.hide
import com.maximbircu.devtools.android.extensions.inflater
import com.maximbircu.devtools.android.extensions.show
import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.presentation.tool.help.DevToolHelpDialogPresenter
import com.maximbircu.devtools.common.presentation.tool.help.DevToolHelpDialogView

class DevToolHelpDialog(
    private val context: Context,
    private val tool: DevTool<*>
) : DevToolHelpDialogView {
    private val presenter = DevToolHelpDialogPresenter.create(this)
    private val binding = LayoutDevToolHelpDialogBinding.inflate(context.inflater)
    private val dialog = MaterialAlertDialogBuilder(context)
        .setPositiveButton(android.R.string.ok, null)
        .setTitle(tool.title)
        .setView(binding.root)
        .create()

    fun show() {
        dialog.show()
        presenter.onToolBind(tool)
    }

    override fun setDescription(description: String) {
        binding.description.text = description
    }

    override fun hideDescription() {
        binding.description.hide()
    }

    override fun setKey(toolKey: String) {
        binding.toolKey.value = toolKey
    }

    override fun setCurrentConfigValue(currentConfigValue: String) {
        binding.currentValue.value = currentConfigValue
    }

    override fun setDefaultConfigValue(defaultConfigValue: String) {
        binding.defaultValue.value = defaultConfigValue
    }

    override fun showCriticalUpdateLabel() {
        binding.criticalUpdate.show()
    }

    override fun hideCriticalUpdateLabel() {
        binding.criticalUpdate.hide()
    }

    override fun showStartupArgument(key: String, value: Any) {
        binding.startupArgument.value = context.resources.getString(
            R.string.dev_tool_help_dialog_startup_argument_value,
            StartupArgumentFlagProvider.getFor(value),
            key
        )
    }
}
