package com.maximbircu.devtools.common

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.reader.DevToolsSource

interface DevToolsParser {
    fun getDevTools(): Map<String, DevTool<Any>>

    companion object {
        fun create(devToolsSources: List<DevToolsSource>): DevToolsParser {
            return DevToolsParserImpl(devToolsSources)
        }
    }
}

private class DevToolsParserImpl(private val sources: List<DevToolsSource>) : DevToolsParser {
    override fun getDevTools(): Map<String, DevTool<Any>> {
        val readers = sources.map { it.getReader() }
        return readers.fold(mapOf(), { accumulator, reader -> accumulator + reader.getDevTools() })
    }
}
