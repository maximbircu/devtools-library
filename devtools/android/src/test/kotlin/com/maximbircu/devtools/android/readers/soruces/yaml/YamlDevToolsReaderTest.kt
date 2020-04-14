package com.maximbircu.devtools.android.readers.soruces.yaml

import com.maximbircu.devtools.android.BaseTest
import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.presentation.tools.text.TextTool
import com.maximbircu.devtools.common.presentation.tools.time.TimeTool
import com.maximbircu.devtools.common.presentation.tools.toggle.ToggleTool
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkConstructor
import org.junit.Test
import org.yaml.snakeyaml.Yaml
import java.io.InputStream
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class YamlDevToolsReaderTest : BaseTest() {
    @Test
    fun `returns empty map if config is empty`() {
        enqueueTools(null)
        val reader = YamlDevToolsReader(mockk(relaxed = true), "".byteInputStream())

        val devTools = reader.getDevTools()

        assertTrue(devTools.isEmpty())
    }

    @Test
    fun `returns proper dev tool`() {
        val expectedTools: Map<String, DevTool<*>> = mapOf(
            "toggle-tool" to ToggleTool(),
            "text-tool" to TextTool(),
            "time-tool" to TimeTool()
        )
        enqueueTools(expectedTools)
        val reader = YamlDevToolsReader(mockk(relaxed = true), "".byteInputStream())

        val actualTools = reader.getDevTools()

        assertEquals(expectedTools, actualTools)
    }

    @Suppress("UNCHECKED_CAST")
    private fun enqueueTools(tools: Map<String, DevTool<*>>? = null) {
        mockkConstructor(Yaml::class)
        every {
            anyConstructed<Yaml>().load<Map<String, DevTool<Any>>>(any<InputStream>())
        } returns tools as? Map<String, DevTool<Any>>?
    }
}
