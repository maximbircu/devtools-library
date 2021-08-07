package com.maximbircu.devtools.common.readers.jsonschema.factories

import com.maximbircu.devtools.common.mvp.BaseTest
import kotlinx.serialization.json.add
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonArray
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
        val enumToolJsonObject = buildJsonObject {
            put("default", "Second Option")
            putJsonArray("enum") {
                add("First Option")
                add("Second Option")
                add("Third Option")
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
        val enumToolJsonObject = buildJsonObject {
            put("allowCustom", true)
            putJsonArray("enum") {
                add("First Option")
                add("Second Option")
                add("Third Option")
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
        val enumToolJsonObject = buildJsonObject {
            put("title", "Enum tool")
            put("default", "Second Option")
            putJsonArray("enum") {
                add("First Option")
                add("Second Option")
                add("Third Option")
            }
            put("allowCustom", true)
        }
        val expectedOptions = enumValues.map { it to it }.toMap()
        val factory = JsonSchemaEnumToolFactory(enumToolJsonObject)

        val actualDevTool = factory.create()

        assertEquals("Second Option", actualDevTool.getDefaultValue())
        assertEquals(expectedOptions, actualDevTool.options)
        assertTrue(actualDevTool.allowCustom)
    }
}
