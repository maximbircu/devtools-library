package com.maximbircu.devtools.configscreens

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.maximbircu.devtools.R.layout
import com.maximbircu.devtools.android.DevToolsConfigurationScreen
import com.maximbircu.devtools.android.readers.yaml
import com.maximbircu.devtools.common.DevTools
import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.presentation.tools.text.TextTool
import com.maximbircu.devtools.common.presentation.tools.toggle.ToggleTool
import com.maximbircu.devtools.common.readers.DevToolsSources
import com.maximbircu.devtools.common.readers.json
import com.maximbircu.devtools.common.readers.memory
import kotlinx.android.synthetic.main.activity_tools_container.devToolsContainer

class CombinedSourcesConfigActivity : AppCompatActivity() {
    private lateinit var devtools: DevTools

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, CombinedSourcesConfigActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_tools_container)

        val memorySource = DevToolsSources.memory(getTools())
        val ymlSource = DevToolsSources.yaml(assets, "dev-tools.yml")
        val jsonSource = DevToolsSources.json(assets.open("dev-tools.json").reader().readText())
        devtools = DevTools.create(memorySource, ymlSource, jsonSource)

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
            }
        )
    }
}
