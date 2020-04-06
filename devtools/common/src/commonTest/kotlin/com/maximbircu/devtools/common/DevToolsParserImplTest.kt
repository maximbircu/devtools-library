package com.maximbircu.devtools.common

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.createTool
import com.maximbircu.devtools.common.core.reader.DevToolsReader
import com.maximbircu.devtools.common.core.reader.DevToolsSource
import com.maximbircu.devtools.common.mvp.BaseTest
import kotlin.test.Test
import kotlin.test.assertEquals

class DevToolsParserImplTest : BaseTest() {
    @Test
    fun `returns a proper map of tools from the provided sources`() {
        val source1Tools = mapOf<String, DevTool<Any>>(
            "first-source-first-tool" to createTool(),
            "first-source-second-tool" to createTool()
        )

        val source2Tools = mapOf<String, DevTool<Any>>(
            "second-source-first-tool" to createTool(),
            "second-source-second-tool" to createTool()
        )

        val sourcesList = listOf(createSource(source1Tools), createSource(source2Tools))

        val parser = DevToolsParser.create(sourcesList)

        assertEquals(parser.getDevTools(), source1Tools + source2Tools)
    }

    private fun createSource(tools: Map<String, DevTool<Any>>) = object : DevToolsSource {
        override fun getReader() = object : DevToolsReader {
            override fun getDevTools(): Map<String, DevTool<Any>> = tools
        }
    }
}
