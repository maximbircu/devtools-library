package com.maximbircu.devtools.common

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.reader.DevToolsReader
import com.maximbircu.devtools.common.core.reader.DevToolsSource
import com.maximbircu.devtools.common.mvp.BaseTest
import com.maximbircu.devtools.common.presentation.tools.group.GroupTool
import com.maximbircu.devtools.common.presentation.tools.text.TextTool
import com.maximbircu.devtools.common.presentation.tools.time.TimeTool
import com.maximbircu.devtools.common.presentation.tools.toggle.ToggleTool
import kotlin.test.Test
import kotlin.test.assertEquals

class DevToolsParserImplTest : BaseTest() {
    @Test
    fun `returns a proper map of tools from the provided sources`() {
        val source1Tools = mapOf<String, DevTool<*>>(
            "first-source-first-tool" to TimeTool(),
            "first-source-second-tool" to TextTool()
        )
        val source2Tools = mapOf<String, DevTool<*>>(
            "second-source-first-tool" to TextTool(),
            "second-source-second-tool" to ToggleTool(),
            "group-tool" to GroupTool(tools = mapOf("child-tool" to TextTool()))
        )
        val sources = listOf(createSource(source1Tools), createSource(source2Tools))
        val parser = DevToolsParser.create(sources)

        val tools = parser.getDevTools()

        assertEquals(source1Tools + source2Tools, tools)

        assertEquals("first-source-first-tool", tools.getValue("first-source-first-tool").key)
        assertEquals("first-source-second-tool", tools.getValue("first-source-second-tool").key)
        assertEquals("second-source-first-tool", tools.getValue("second-source-first-tool").key)
        assertEquals("second-source-second-tool", tools.getValue("second-source-second-tool").key)
        assertEquals("second-source-second-tool", tools.getValue("second-source-second-tool").key)

        val groupTool: GroupTool = tools.getValue("group-tool") as GroupTool
        assertEquals("group-tool", tools.getValue("group-tool").key)
        assertEquals("group-tool.child-tool", groupTool.tools.getValue("child-tool").key)
    }

    private fun createSource(tools: Map<String, DevTool<*>>) = object : DevToolsSource {
        override fun getReader() = object : DevToolsReader {
            override fun getDevTools(): Map<String, DevTool<*>> = tools
        }
    }
}
