package com.maximbircu.devtools.android.readers

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.reader.DevToolsReader
import com.maximbircu.devtools.common.core.reader.DevToolsSource
import com.maximbircu.devtools.common.readers.DevToolsSources

fun DevToolsSources.memory(devTools: Map<String, DevTool<*>>): DevToolsSource {
    return MemoryDevToolsSource(devTools)
}

@Suppress("UNCHECKED_CAST")
private class MemoryDevToolsSource(private val devTools: Map<String, DevTool<*>>) : DevToolsSource {
    override fun getReader(): DevToolsReader = object : DevToolsReader {
        override fun getDevTools() = devTools as Map<String, DevTool<Any>>
    }
}
