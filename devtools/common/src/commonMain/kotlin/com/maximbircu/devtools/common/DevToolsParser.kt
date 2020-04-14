package com.maximbircu.devtools.common

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.reader.DevToolsSource
import com.maximbircu.devtools.common.presentation.tools.group.GroupTool

interface DevToolsParser {
    fun getDevTools(): Map<String, DevTool<*>>

    companion object {
        fun create(devToolsSources: List<DevToolsSource>): DevToolsParser {
            return DevToolsParserImpl(devToolsSources)
        }
    }
}

private class DevToolsParserImpl(private val sources: List<DevToolsSource>) : DevToolsParser {
    override fun getDevTools(): Map<String, DevTool<*>> {
        val readers = sources.map { it.getReader() }
        return readers.fold(mapOf()) { accumulator, reader ->
            val tools = reader.getDevTools()
            tools.setKeys()
            accumulator + tools
        }
    }

    private fun Map<String, DevTool<*>>.setKeys(keyPrefix: String = "") {
        forEach { (key, tool) ->
            tool.key = if (keyPrefix.isNotEmpty()) "$keyPrefix.$key" else key
            if (tool is GroupTool) {
                tool.tools.setKeys(
                    if (keyPrefix.isNotEmpty()) "$keyPrefix.${tool.key}" else tool.key
                )
            }
        }
    }
}
