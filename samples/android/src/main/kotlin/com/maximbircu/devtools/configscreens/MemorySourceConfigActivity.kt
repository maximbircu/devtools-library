package com.maximbircu.devtools.configscreens

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.maximbircu.devtools.CustomEnumOptionsProvider
import com.maximbircu.devtools.R.layout
import com.maximbircu.devtools.android.DevToolsConfigurationScreen
import com.maximbircu.devtools.common.DevTools
import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.presentation.tools.enum.EnumOptionsProvider
import com.maximbircu.devtools.common.presentation.tools.enum.EnumTool
import com.maximbircu.devtools.common.presentation.tools.group.GroupTool
import com.maximbircu.devtools.common.presentation.tools.text.TextTool
import com.maximbircu.devtools.common.presentation.tools.time.TimeTool
import com.maximbircu.devtools.common.presentation.tools.toggle.ToggleTool
import com.maximbircu.devtools.common.readers.DevToolsSources
import com.maximbircu.devtools.common.readers.memory
import kotlinx.android.synthetic.main.activity_tools_container.devToolsContainer

@Suppress("LargeClass")
class MemorySourceConfigActivity : AppCompatActivity() {
    private lateinit var devtools: DevTools

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, MemorySourceConfigActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_tools_container)

        val source = DevToolsSources.memory(getTools())
        devtools = DevTools.create(source)

        DevToolsConfigurationScreen.attachToView(devToolsContainer, devtools)
    }

    @Suppress("LongMethod", "ComplexMethod")
    private fun getTools(): Map<String, DevTool<*>> {
        return mapOf(
            "memory-toggle-tool" to ToggleTool(defaultValue = false).apply {
                title = "Toggle tool"
                description = "A boolean configuration value dev tool"
                canBeDisabled = true
                defaultEnabledValue = false
            },

            "memory-text-tool" to TextTool(
                defaultValue = "Here can go any text value",
                hint = "String config value"
            ).apply {
                title = "Text tool (String)"
                description = "A text configuration value tool"
                canBeDisabled = true
                defaultEnabledValue = false
            },
            "memory-text-tool-integer" to TextTool(
                defaultValue = 3,
                hint = "Integer number config value"
            ).apply {
                title = "Text tool (Integer)"
                description = "An integer number configuration value tool"
                canBeDisabled = true
                defaultEnabledValue = false
            },
            "memory-text-tool-float" to TextTool(
                defaultValue = 3.4f,
                hint = "Floating point number config value"
            ).apply {
                title = "Text tool (Floating point)"
                description = "A floating-point number configuration value tool"
                canBeDisabled = true
                defaultEnabledValue = false
            },

            "memory-time-tool" to TimeTool(
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
            },

            "memory-enum-tool" to EnumTool(
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
            },
            "memory-enum-custom-options-provider-tool" to EnumTool(
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
            },

            "memory-tools-group" to GroupTool(
                tools = mapOf(
                    "memory-toggle-tool" to ToggleTool(defaultValue = false).apply {
                        title = "Toggle tool"
                        description = "A boolean configuration value dev tool"
                        canBeDisabled = true
                        defaultEnabledValue = false
                    },
                    "memory-text-tool" to TextTool(
                        defaultValue = "Here can go any text value",
                        hint = "String config value"
                    ).apply {
                        title = "Text tool (String)"
                        description = "A text configuration value tool"
                        canBeDisabled = true
                        defaultEnabledValue = false
                    },
                    "memory-time-tool" to TimeTool(
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
                    },
                    "memory-enum-tool" to EnumTool(
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
                    }
                )
            ).apply {
                title = "Tools group"
            }
        ).apply {
            forEach { (key, value) -> value.key = key }
        }
    }
}