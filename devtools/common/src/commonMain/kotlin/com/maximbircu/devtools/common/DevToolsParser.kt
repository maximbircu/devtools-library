package com.maximbircu.devtools.common

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.reader.DevToolsReader
import com.maximbircu.devtools.common.core.reader.DevToolsSource

class DevToolsParser(private val readers: List<DevToolsReader>) {
    companion object {
        fun create(devToolsSources: List<DevToolsSource>): DevToolsParser {
            return DevToolsParser(devToolsSources.map { it.getReader() })
        }
    }

    fun getDevTools(): Map<String, DevTool<Any>> {
        return readers.fold(mapOf(), { accumulator, devToolsReader ->
            accumulator + devToolsReader.getDevTools()
        })
    }
}
