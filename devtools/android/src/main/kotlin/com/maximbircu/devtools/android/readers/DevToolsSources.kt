package com.maximbircu.devtools.android.readers

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.reader.DevToolsReader
import com.maximbircu.devtools.common.core.reader.DevToolsSource
import com.maximbircu.devtools.common.presentation.tools.toggle.ToggleTool
import com.maximbircu.devtools.common.readers.DevToolsSources

fun DevToolsSources.memory(): DevToolsSource = object : DevToolsSource {
    override fun getReader(): DevToolsReader {
        return object :  DevToolsReader {
            override fun getDevTools(): Map<String, DevTool<Any>> {
                val toggleTool = ToggleTool(defaultValue = false)
                toggleTool.title = "Toggle dev tool"
                return mapOf("toggle-tool" to toggleTool) as Map<String, DevTool<Any>>
            }
        }
    }
}

