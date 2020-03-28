package com.maximbircu.devtools.android.readers.soruces

import com.maximbircu.devtools.android.BaseTest
import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.presentation.tools.toggle.ToggleTool
import org.junit.Test
import kotlin.test.assertEquals

class MemoryDevToolsSourceTest : BaseTest() {
    @Test
    fun `returns proper dev tools`() {
        val tools: Map<String, DevTool<*>> = mapOf(
            "first-toggle-tool" to ToggleTool(false),
            "second-toggle-tool" to ToggleTool(false)
        )

        val source = MemoryDevToolsSource(tools)

        assertEquals(tools, source.getReader().getDevTools())
    }
}
