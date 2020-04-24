package com.maximbircu.devtools.common.presentation.tools.enum

import com.maximbircu.devtools.common.core.mvp.BaseView

/**
 * Presents a dev tool which is a ble to manipulate enum configuration values.
 */
interface EnumToolView : BaseView {
    /**
     * Should present the configuration [value] in a text form to the user.
     *
     * @param value the currently selected configuration value, note that this value is just an
     * in-memory one and might not be persisted.
     */
    fun showConfigurationValue(value: String)

    /**
     * Should present a compact option selector aka a single choice short list of selectable chips
     * or radio buttons.
     *
     * @param tool should be used to extract the [EnumTool.options] that might be selected
     * @param onNewOptionSelected should be invoked whenever a new option is selected by the user
     */
    fun showCompactOptionsSelector(tool: EnumTool, onNewOptionSelected: (String) -> Unit)

    /**
     * Should present an option selector in for of a scrollable single choice selectable items
     * inside a dialog or a separate screen.
     *
     * @param tool should be used to extract the [EnumTool.options] that might be selected
     * @param onNewOptionSelected should be invoked whenever a new option is selected by the user
     */
    fun showOptionSelectorDialog(tool: EnumTool, onNewOptionSelected: (String) -> Unit)
}
