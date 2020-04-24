package com.maximbircu.devtools.common.presentation.tools.group

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.mvp.BaseView
import com.maximbircu.devtools.common.presentation.tool.DevToolView

/**
 * Presents a group of devtools to the user.
 */
interface GroupToolView : BaseView {
    /**
     * Should return the configuration screen devtools views that are displayed by the group.
     */
    val devToolsViews: List<DevToolView>

    /**
     * Should show a list of dev tools in the form of a group.
     *
     * @property tools the group member tools
     */
    fun showTools(tools: List<DevTool<*>>)

    fun refreshToolData()
}
