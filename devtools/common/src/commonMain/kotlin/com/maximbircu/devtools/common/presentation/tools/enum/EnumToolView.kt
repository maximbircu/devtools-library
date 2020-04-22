package com.maximbircu.devtools.common.presentation.tools.enum

import com.maximbircu.devtools.common.core.mvp.BaseView

/**
 * Presents a dev tool which is a ble to manipulate enum configuration values.
 */
interface EnumToolView : BaseView {
    fun showConfigurationValue(value: String)

    fun showCompactOptionsSelector(tool: EnumTool, onNewOptionSelected: (String) -> Unit)

    fun showOptionSelectorDialog(tool: EnumTool, onNewOptionSelected: (String) -> Unit)
}
