package com.maximbircu.devtools.common.presentation.list

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.mvp.BaseView

interface DevToolsListView : BaseView {
    fun showDevTools(tools: List<DevTool<*>>)
    fun updateDevTools()
}
