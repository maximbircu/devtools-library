package com.maximbircu.devtools.android.readers.soruces.yaml

import com.maximbircu.devtools.android.BaseTest
import com.maximbircu.devtools.common.presentation.tools.toggle.ToggleTool
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkConstructor
import io.mockk.slot
import io.mockk.verify
import org.junit.Test
import org.yaml.snakeyaml.TypeDescription
import org.yaml.snakeyaml.constructor.Constructor
import kotlin.test.assertEquals

class YamlDevToolsTypesRegistryImplTest : BaseTest() {
    @Test
    fun `registers type descriptors`() {
        val typesRegistryData = """
            com.maximbircu.devtools.common.presentation.tools.toggle.ToggleTool : "!toggle"
        """.trimIndent().byteInputStream()
        val typeDescriptionSlot = slot<TypeDescription>()
        mockkConstructor(Constructor::class)
        every {
            anyConstructed<Constructor>().addTypeDescription(capture(typeDescriptionSlot))
        } returns mockk(relaxed = true)

        YamlDevToolsTypesRegistry.create(typesRegistryData)

        assertEquals(ToggleTool::class.java, typeDescriptionSlot.captured.type)
        assertEquals("!toggle", typeDescriptionSlot.captured.tag.value)
        verify(exactly = 1) { anyConstructed<Constructor>().addTypeDescription(any()) }
    }
}
