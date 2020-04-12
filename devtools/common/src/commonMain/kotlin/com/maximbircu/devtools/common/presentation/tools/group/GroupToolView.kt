package com.maximbircu.devtools.common.presentation.tools.group

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.mvp.BaseView
import com.maximbircu.devtools.common.presentation.tool.DevToolView

interface GroupToolView : BaseView {
    val devToolsViews: List<DevToolView>

    fun showTools(tools: List<DevTool<*>>)
}
