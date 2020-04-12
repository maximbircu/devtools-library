package com.maximbircu.devtools.common.presentation.tools.enum

import com.maximbircu.devtools.common.core.PreferencesDevTool

/**
 * Represents a dev tool able to manipulate enum configuration values.
 * Use this tool when you have a limited configuration value options.
 *
 * @param defaultValueKey the key of the configuration value that should be selected by default
 * @param allowCustom if true then the tool accepts values that are not present in [options]
 * @param optionsProvider will be used to extract config options in case it will be provided
 */
class EnumTool(
    private val defaultValueKey: String? = null,
    val allowCustom: Boolean = false,
    private val optionsProvider: EnumOptionsProvider? = null
) : PreferencesDevTool<String>() {
    /** A key-value pairs collection of available configuration options the tool is supporting. */
    val options: Map<String, String> = mapOf()
        get() = optionsProvider?.getOptions() ?: field

    override fun getDefaultValue(): String {
        assertDefaultValueKeyProvided()
        return options[defaultValueKey]
            ?: throw IllegalArgumentException("No option found for \"$defaultValueKey\" key")
    }

    fun getOptionNameForValue(value: Any) = options.keys.firstOrNull { options[it] == value }

    private fun assertDefaultValueKeyProvided() {
        if (defaultValueKey == null) {
            throw NullPointerException("Default val required")
        }
    }
}
