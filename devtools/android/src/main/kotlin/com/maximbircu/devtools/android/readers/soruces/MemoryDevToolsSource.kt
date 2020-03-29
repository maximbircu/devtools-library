package com.maximbircu.devtools.android.readers.soruces

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.reader.DevToolsReader
import com.maximbircu.devtools.common.core.reader.DevToolsSource

internal class MemoryDevToolsSource(private val tools: Map<String, DevTool<*>>) : DevToolsSource {
    override fun getReader(): DevToolsReader = object : DevToolsReader {
        override fun getDevTools() = tools
    }
}
