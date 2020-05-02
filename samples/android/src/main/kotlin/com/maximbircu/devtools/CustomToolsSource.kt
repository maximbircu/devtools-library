package com.maximbircu.devtools

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.reader.DevToolsReader
import com.maximbircu.devtools.common.core.reader.DevToolsSource
import com.maximbircu.devtools.customtool.MyCustomTool

val myCustomTools: Map<String, DevTool<*>> = mapOf(
    "my-custom-tool" to MyCustomTool().apply {
        title = "Custom tool"
        description = "A custom dev tool implemented inside the library consumer"
        canBeDisabled = true
        defaultEnabledValue = false
    }
)

class MyCustomDevToolsSource : DevToolsSource {
    override fun getReader(): DevToolsReader {
        return object : DevToolsReader {
            override fun getDevTools(): Map<String, DevTool<*>> {
                return myCustomTools
            }
        }
    }
}
