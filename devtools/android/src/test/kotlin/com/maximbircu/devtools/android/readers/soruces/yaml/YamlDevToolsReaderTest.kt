package com.maximbircu.devtools.android.readers.soruces.yaml

import com.maximbircu.devtools.android.BaseTest
import com.maximbircu.devtools.common.presentation.tools.toggle.ToggleTool
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class YamlDevToolsReaderTest : BaseTest() {
    @Test
    fun `returns empty map if config is empty`() {
        val devToolsConfig = ""
        val reader = YamlDevToolsReader(createTypeRegistry(), devToolsConfig.byteInputStream())

        val devTools = reader.getDevTools()

        assertTrue(devTools.isEmpty())
    }

    @Test
    fun `returns proper dev tool`() {
        val expectedTool = ToggleTool().apply { key = "toggle-dev-tool" }
        val devToolsConfig = """
            toggle-dev-tool: !toggle {
              title: "Toggle dev tool"
            }
        """.trimIndent().byteInputStream()
        val reader = YamlDevToolsReader(createTypeRegistry(), devToolsConfig)

        val devTools = reader.getDevTools()

        assertEquals(expectedTool, devTools["toggle-dev-tool"] as ToggleTool)
    }

    private fun createTypeRegistry(): YamlDevToolsTypesRegistry = YamlDevToolsTypesRegistry.create(
        """com.maximbircu.devtools.common.presentation.tools.toggle.ToggleTool : "!toggle""""
            .trimIndent().byteInputStream()
    )
}
