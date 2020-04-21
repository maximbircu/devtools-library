package com.maximbircu.devtools.common

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.createTool
import com.maximbircu.devtools.common.core.reader.DevToolsReader
import com.maximbircu.devtools.common.core.reader.DevToolsSource
import com.maximbircu.devtools.common.mvp.BaseTest
import com.maximbircu.devtools.common.presentation.tools.group.GroupTool
import io.mockk.mockkConstructor
import io.mockk.spyk
import io.mockk.verify
import kotlin.test.Test
import kotlin.test.assertEquals

class DevToolsParserImplTest : BaseTest() {
    @Test
    fun `returns a proper map of tools from the provided sources`() {
        mockkConstructor(DevTool::class)
        val source1Tools = mapOf<String, DevTool<*>>(
            "first-source-first-tool" to createTool(),
            "first-source-second-tool" to createTool()
        )
        val source2Tools = mapOf<String, DevTool<*>>(
            "second-source-first-tool" to createTool(),
            "second-source-second-tool" to createTool(),
            "group-tool" to spyk(GroupTool(mapOf("child-tool" to createTool())))
        )
        val sources = listOf(createSource(source1Tools), createSource(source2Tools))
        val parser = DevToolsParser.create(sources)

        val tools = parser.getDevTools()

        assertEquals(source1Tools + source2Tools, tools)

        verify { tools.getValue("first-source-first-tool").key = "first-source-first-tool" }
        verify { tools.getValue("first-source-second-tool").key = "first-source-second-tool" }
        verify { tools.getValue("second-source-first-tool").key = "second-source-first-tool" }
        verify { tools.getValue("second-source-second-tool").key = "second-source-second-tool" }

        val childTools = (tools.getValue("group-tool") as GroupTool).tools
        println(childTools.getValue("child-tool").key)
        verify { tools.getValue("group-tool").key = "group-tool" }
        verify { childTools.getValue("child-tool").key = "group-tool.child-tool" }
    }

    private fun createSource(tools: Map<String, DevTool<*>>) = object : DevToolsSource {
        override fun getReader() = object : DevToolsReader {
            override fun getDevTools(): Map<String, DevTool<*>> = tools
        }
    }
}
