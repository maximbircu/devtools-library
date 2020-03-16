package com.maximbircu.devtools.common.presentation.configscreen

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.mvp.BaseView

/**
 * The configuration interface which displays all dev tools parsed from the configuration and allows
 * the user to manipulate their state and configuration values.
 */
interface ConfigScreenView : BaseView {
    /**
     * Takes a collection of devtools as parameter and displays an interactive list of dev tools to
     * the user.
     *
     * @param tools the collection of the dev tools to be presented to the user.
     */
    fun showDevTools(tools: List<DevTool<*>>)

    /**
     * Called when the user wants to apply thew changes he introduced.
     */
    fun triggerConfigUpdate()
}
