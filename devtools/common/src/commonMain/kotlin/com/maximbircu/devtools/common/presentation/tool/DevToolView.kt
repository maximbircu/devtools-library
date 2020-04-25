package com.maximbircu.devtools.common.presentation.tool

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.mvp.BaseView

/**
 * Should present a dev tool to the user. The tool should have all required controls so that the
 * user will be able to update its state and configuration value.
 */
interface DevToolView : BaseView {
    /**
     * Should display the tool title to the user.
     */
    fun setTitle(title: String?)

    /**
     * Should make the tool enable/disable toggle visible to the user.
     */
    fun showEnableToggle()

    /**
     * Should make the tool enable/disable toggle invisible to the user.
     */
    fun hideEnableToggle()

    /**
     * Should update the tool view such that the user will not be able to access it in case
     * [isEnabled] is false and vice-versa in case its true.
     *
     * @param isEnabled true if the tool should be enabled and false vice-versa
     */
    fun setToolEnableState(isEnabled: Boolean)

    /**
     * Should present a [DevTool] context menu to the user.
     */
    fun showToolContextMenu()

    /**
     * This method will be invoked whenever the [DevTool] state will be changed.
     * In order to make the view refresh and show the new data.
     *
     * @param tool the [DevTool] object which contains the new state
     */
    fun refreshToolData(tool: DevTool<*>)

    /**
     * Should present a
     * [com.maximbircu.devtools.common.presentation.tool.help.DevToolHelpDialogView].
     */
    fun showHelpDialog(tool: DevTool<*>)
}
