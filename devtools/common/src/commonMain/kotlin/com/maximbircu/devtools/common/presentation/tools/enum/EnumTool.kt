package com.maximbircu.devtools.common.presentation.tools.enum

import com.maximbircu.devtools.common.core.PreferencesDevTool

class EnumTool(
    private val defaultValueKey: String? = null,
    val allowCustom: Boolean = false,
    private val optionsProvider: EnumOptionsProvider? = null
) : PreferencesDevTool<String>() {
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
