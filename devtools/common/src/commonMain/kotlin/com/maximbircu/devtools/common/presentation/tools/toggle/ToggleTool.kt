package com.maximbircu.devtools.common.presentation.tools.toggle

import com.maximbircu.devtools.common.core.PreferencesDevTool

/**
 * Represents a boolean configuration value dev tool.
 * Holds and shares the dev tool metadata, and the configuration it manipulates.
 *
 * @property defaultValue default value which will be set from on of the supported tools sources
 */
data class ToggleTool(
    private val defaultValue: Boolean = false
) : PreferencesDevTool<Boolean>() {
    override fun getDefaultValue(): Boolean = defaultValue
}
