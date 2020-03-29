package com.maximbircu.devtools.android.readers.soruces.yaml

import com.maximbircu.devtools.android.BaseTest
import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.presentation.tools.toggle.ToggleTool
import io.mockk.every
import io.mockk.mockkConstructor
import org.junit.Test
import org.yaml.snakeyaml.Yaml
import java.io.InputStream
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
    @Suppress("UNCHECKED_CAST")
    fun `returns proper dev tool`() {
        mockkConstructor(Yaml::class)
        val reader = YamlDevToolsReader(createTypeRegistry(), "".byteInputStream())
        every {
            anyConstructed<Yaml>().load<Map<String, DevTool<Any>>>(any<InputStream>())
        } returns mapOf("toggle-tool" to ToggleTool()) as Map<String, DevTool<Any>>

        val tools = reader.getDevTools()

        assertEquals(tools.getValue("toggle-tool").key, "toggle-tool")
    }

    private fun createTypeRegistry(): YamlDevToolsTypesRegistry = YamlDevToolsTypesRegistry.create(
        """com.maximbircu.devtools.common.presentation.tools.toggle.ToggleTool : "!toggle""""
            .trimIndent().byteInputStream()
    )
}
