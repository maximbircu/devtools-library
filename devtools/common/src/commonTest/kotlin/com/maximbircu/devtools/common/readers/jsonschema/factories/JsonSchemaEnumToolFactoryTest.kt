package com.maximbircu.devtools.common.readers.jsonschema.factories

import com.maximbircu.devtools.common.mvp.BaseTest
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class JsonSchemaEnumToolFactoryTest : BaseTest() {
    private val enumValues: List<String>
        get() = listOf("First Option", "Second Option", "Third Option")

    @Test
    fun `creates a proper enum dev tool with default value provided`() {
        val enumToolJsonObject = json {
            "default" to "Second Option"
            "enum" to jsonArray {
                +"First Option"
                +"Second Option"
                +"Third Option"
            }
        }
        val expectedOptions = enumValues.map { it to it }.toMap()
        val factory = JsonSchemaEnumToolFactory(enumToolJsonObject)

        val actualDevTool = factory.create()

        assertEquals("Second Option", actualDevTool.getDefaultValue())
        assertEquals(expectedOptions, actualDevTool.options)
        assertFalse(actualDevTool.allowCustom)
    }

    @Test
    fun `creates a proper enum dev tool with allow custom flags provided`() {
        val enumToolJsonObject = json {
            "allowCustom" to true
            "enum" to jsonArray {
                +"First Option"
                +"Second Option"
                +"Third Option"
            }
        }
        val expectedOptions = enumValues.map { it to it }.toMap()
        val factory = JsonSchemaEnumToolFactory(enumToolJsonObject)

        val actualDevTool = factory.create()

        assertFailsWith(NullPointerException::class) { actualDevTool.getDefaultValue() }
        assertEquals(expectedOptions, actualDevTool.options)
        assertTrue(actualDevTool.allowCustom)
    }

    @Test
    fun `creates a proper enum dev tool with default value and allow custom flags provided`() {
        val enumToolJsonObject = json {
            "title" to "Enum tool"
            "default" to "Second Option"
            "enum" to jsonArray {
                +"First Option"
                +"Second Option"
                +"Third Option"
            }
            "allowCustom" to true
        }
        val expectedOptions = enumValues.map { it to it }.toMap()
        val factory = JsonSchemaEnumToolFactory(enumToolJsonObject)

        val actualDevTool = factory.create()

        assertEquals("Second Option", actualDevTool.getDefaultValue())
        assertEquals(expectedOptions, actualDevTool.options)
        assertTrue(actualDevTool.allowCustom)
    }
}
