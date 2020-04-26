package com.maximbircu.devtools.common.presentation.tool.help

import com.maximbircu.devtools.common.core.mvp.BaseView

/**
 * Should present a dev tool help dialog, which will show the dev tool current state and more useful
 * tool configuration information.
 */
interface DevToolHelpDialogView : BaseView {
    /**
     * Should hide the description label for cases when the tool description was not provided.
     * @see [com.maximbircu.devtools.common.core.DevTool.description]
     */
    fun hideDescription()

    /**
     * Should present the dev tool description to the user in a text format.
     *
     * @param description the dev tool description
     * @see [com.maximbircu.devtools.common.core.DevTool.description]
     */
    fun setDescription(description: String)

    /**
     * Should present the dev tool key to the user in a text format.
     *
     * @param toolKey the dev tool key
     * @see [com.maximbircu.devtools.common.core.DevTool.key]
     */
    fun setKey(toolKey: String)

    /**
     * Should present the dev tool current config value to the user in a text format.
     *
     * @param currentConfigValue the current dev tool configuration value
     * @see [com.maximbircu.devtools.common.core.DevTool.value]
     */
    fun setCurrentConfigValue(currentConfigValue: String)

    /**
     * Should present the dev tool default config value to the user in a text format.
     *
     * @param defaultConfigValue the default dev tool configuration value
     * @see [com.maximbircu.devtools.common.core.DevTool.getDefaultValue]
     */
    fun setDefaultConfigValue(defaultConfigValue: String)

    /**
     * Should show a warning label that will notify the user that the dev tool will trigger a
     * critical update.
     */
    fun showCriticalUpdateLabel()

    /**
     * Should hide the warning label that notifies the user that the dev tool will trigger a
     * critical update because the tool is not critical.
     */
    fun hideCriticalUpdateLabel()
}
