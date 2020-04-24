package com.maximbircu.devtools.common.presentation.tool.help

import com.maximbircu.devtools.common.core.mvp.BaseView

interface DevToolHelpDialogView : BaseView {
    fun hideDescription()
    fun setDescription(description: String)
    fun setKey(toolKey: String)
    fun setCurrentConfigValue(currentConfigValue: String)
    fun setDefaultConfigValue(defaultConfigValue: String)
}
