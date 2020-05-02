package com.maximbircu.devtools.common.presentation.tools.group

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.presentation.tool.DevToolView

/**
 * Presents a group of devtools to the user.
 */
interface GroupToolView : DevToolView {
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

    /**
     * This method will be invoked whenever the [DevTool] state will be changed.
     * To make the view refresh and show the new data.
     */
    fun refreshToolData()
}
