package com.maximbircu.devtools.android.readers.soruces.yaml

import com.maximbircu.devtools.android.BaseTest
import com.maximbircu.devtools.android.utils.mockk
import com.maximbircu.devtools.common.presentation.tools.toggle.ToggleTool
import io.mockk.every
import io.mockk.mockkConstructor
import org.junit.Test
import org.yaml.snakeyaml.Yaml
import java.io.InputStream
import kotlin.test.assertTrue

class YamlDevToolsSourceTest : BaseTest() {
    @Test
    fun `test`() {
        setUpYaml(mapOf(ToggleTool::class.java.name to "!toggle"))
        val source = YamlDevToolsSource(mockk(relaxed = true), "".byteInputStream())

        val reader = source.getReader()

        assertTrue(reader is YamlDevToolsReader)
    }

    private fun setUpYaml(types: Map<String, String>) {
        mockkConstructor(Yaml::class)
        every { anyConstructed<Yaml>().load<Map<String, String>>(any<InputStream>()) } returns types
    }
}
