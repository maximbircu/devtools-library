package com.maximbircu.devtools.android.readers

import com.maximbircu.devtools.android.readers.soruces.MemoryDevToolsSource
import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.reader.DevToolsSource
import com.maximbircu.devtools.common.readers.DevToolsSources

fun DevToolsSources.memory(devTools: Map<String, DevTool<*>>): DevToolsSource {
    return MemoryDevToolsSource(devTools)
}
