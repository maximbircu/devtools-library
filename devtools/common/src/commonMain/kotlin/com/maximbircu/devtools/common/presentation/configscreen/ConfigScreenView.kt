package com.maximbircu.devtools.common.presentation.configscreen

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.mvp.BaseView

interface ConfigScreenView : BaseView {
    fun showDevTools(tools: List<DevTool<*>>)
    fun triggerConfigUpdate()
}
