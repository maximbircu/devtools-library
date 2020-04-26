package com.maximbircu.devtools

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.presentation.tools.enum.EnumOptionsProvider
import com.maximbircu.devtools.common.presentation.tools.enum.EnumTool
import com.maximbircu.devtools.common.presentation.tools.group.GroupTool
import com.maximbircu.devtools.common.presentation.tools.text.TextTool
import com.maximbircu.devtools.common.presentation.tools.time.TimeTool
import com.maximbircu.devtools.common.presentation.tools.toggle.ToggleTool

@Suppress("LargeClass")
object MemoryDevToolsProvider {
    val tools: Map<String, DevTool<*>>
        get() = mapOf(
            "toggle-tool" to ToggleTool(defaultValue = false).apply {
                title = "Toggle tool"
                description = "A boolean configuration value dev tool"
                canBeDisabled = true
                defaultEnabledValue = false
                isCritical = true
            },

            "text-tool" to TextTool(
                defaultValue = "Here can go any text value",
                hint = "String config value"
            ).apply {
                title = "Text tool (String)"
                description = "A text configuration value tool"
                canBeDisabled = true
                defaultEnabledValue = false
                isCritical = false
            },
            "text-tool-integer" to TextTool(
                defaultValue = 3,
                hint = "Integer number config value"
            ).apply {
                title = "Text tool (Integer)"
                description = "An integer number configuration value tool"
                canBeDisabled = true
                defaultEnabledValue = false
                isCritical = true
            },
            "text-tool-float" to TextTool(
                defaultValue = 3.4f,
                hint = "Floating point number config value"
            ).apply {
                title = "Text tool (Floating point)"
                description = "A floating-point number configuration value tool"
                canBeDisabled = true
                defaultEnabledValue = false
                isCritical = false
            },

            "time-tool" to TimeTool(
                days = 1,
                hours = 2,
                minutes = 3,
                seconds = 4,
                milliseconds = 5
            ).apply {
                title = "Time tool"
                description = "A time configuration value tool"
                canBeDisabled = true
                defaultEnabledValue = false
                isCritical = true
            },

            "enum-tool" to EnumTool(
                defaultValueKey = "first-option",
                allowCustom = true,
                optionsProvider = object : EnumOptionsProvider {
                    override fun getOptions() = mapOf(
                        "first-option" to "First Option",
                        "second-option" to "Second Option",
                        "third-option" to "Third Option"
                    )
                }
            ).apply {
                title = "Enum tool"
                description = "An enum configuration value tool"
                canBeDisabled = true
                defaultEnabledValue = false
                isCritical = false
            },
            "enum-custom-options-provider-tool" to EnumTool(
                defaultValueKey = "first-option",
                allowCustom = true,
                optionsProvider = CustomEnumOptionsProvider(
                    fileName = "enum-options.json"
                )
            ).apply {
                title = "Enum tool [Custom Provider]"
                description = "An enum configuration value tool with a custom options provider"
                canBeDisabled = true
                defaultEnabledValue = false
                isCritical = true
            },

            "tools-group" to GroupTool(
                tools = mapOf(
                    "toggle-tool" to ToggleTool(defaultValue = false).apply {
                        title = "Toggle tool"
                        description = "A boolean configuration value dev tool"
                        canBeDisabled = true
                        defaultEnabledValue = false
                        isCritical = false
                    },
                    "text-tool" to TextTool(
                        defaultValue = "Here can go any text value",
                        hint = "String config value"
                    ).apply {
                        title = "Text tool (String)"
                        description = "A text configuration value tool"
                        canBeDisabled = true
                        defaultEnabledValue = false
                        isCritical = true
                    },
                    "time-tool" to TimeTool(
                        days = 1,
                        hours = 2,
                        minutes = 3,
                        seconds = 4,
                        milliseconds = 5
                    ).apply {
                        title = "Time tool"
                        description = "A time configuration value tool"
                        canBeDisabled = true
                        defaultEnabledValue = false
                        isCritical = false
                    },
                    "enum-tool" to EnumTool(
                        defaultValueKey = "first-option",
                        allowCustom = true,
                        optionsProvider = object : EnumOptionsProvider {
                            override fun getOptions() = mapOf(
                                "first-option" to "First Option",
                                "second-option" to "Second Option",
                                "third-option" to "Third Option"
                            )
                        }
                    ).apply {
                        title = "Enum tool"
                        description = "An enum configuration value tool"
                        canBeDisabled = true
                        defaultEnabledValue = false
                        isCritical = true
                    }
                )
            ).apply {
                title = "Tools group"
            }
        ).apply {
            forEach { (key, value) -> value.key = key }
        }
}
