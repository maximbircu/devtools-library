package com.maximbircu.devtools.common.presentation.list

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.mvp.BaseView
import com.maximbircu.devtools.common.presentation.tool.DevToolView

/**
 * Should present a collection of dev tools to the user.
 */
interface DevToolsListView : BaseView {
    /**
     * Should provide the list of [DevToolView] the view is displaying.
     */
    val devToolViews: List<DevToolView>

    /**
     * Should display [tools] in form of a list to the user.
     *
     * @param tools the collection of dev tools to be presented to the user.
     */
    fun showDevTools(tools: List<DevTool<*>>)
}
