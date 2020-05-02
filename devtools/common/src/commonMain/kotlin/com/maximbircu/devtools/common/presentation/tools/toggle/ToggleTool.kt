package com.maximbircu.devtools.common.presentation.tools.toggle

import com.maximbircu.devtools.common.core.PreferencesDevTool

/**
 * Represents a dev tool able to manipulate boolean configuration values.
 *
 * @property default the default boolean configuration value
 */
data class ToggleTool(
    private val default: Boolean = false
) : PreferencesDevTool<Boolean>() {
    override fun getDefaultValue(): Boolean = default
}
