package com.maximbircu.devtools.android.readers.soruces.yaml

import com.maximbircu.devtools.android.BaseTest
import com.maximbircu.devtools.common.presentation.tools.toggle.ToggleTool
import io.mockk.CapturingSlot
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkConstructor
import io.mockk.slot
import io.mockk.verify
import org.junit.Test
import org.yaml.snakeyaml.TypeDescription
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor
import java.io.InputStream
import kotlin.test.assertEquals

class YamlDevToolsTypesRegistryImplTest : BaseTest() {
    @Test
    fun `registers type descriptors`() {
        val typeDescriptionSlot: CapturingSlot<TypeDescription> = slot()
        setUpConstructor(typeDescriptionSlot)
        setUpYaml(mapOf(ToggleTool::class.java.name to "!toggle"))

        YamlDevToolsTypesRegistry.create("".trimIndent().byteInputStream())

        assertEquals(ToggleTool::class.java, typeDescriptionSlot.captured.type)
        assertEquals("!toggle", typeDescriptionSlot.captured.tag.value)
        verify(exactly = 1) { anyConstructed<Constructor>().addTypeDescription(any()) }
    }

    private fun setUpConstructor(slot: CapturingSlot<TypeDescription>) {
        mockkConstructor(Constructor::class)
        every {
            anyConstructed<Constructor>().addTypeDescription(capture(slot))
        } returns mockk(relaxed = true)
    }

    private fun setUpYaml(types: Map<String, String>) {
        mockkConstructor(Yaml::class)
        every { anyConstructed<Yaml>().load<Map<String, String>>(any<InputStream>()) } returns types
    }
}
