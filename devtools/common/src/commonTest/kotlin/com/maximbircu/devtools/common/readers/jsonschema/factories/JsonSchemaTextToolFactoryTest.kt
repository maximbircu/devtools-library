package com.maximbircu.devtools.common.readers.jsonschema.factories

import com.maximbircu.devtools.common.mvp.BaseTest
import com.maximbircu.devtools.common.presentation.tools.text.TextTool
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlin.test.Test
import kotlin.test.assertEquals

class JsonSchemaTextToolFactoryTest : BaseTest() {
    @Test
    fun `creates a proper text tool without default and hint values provided`() {
        val jsonObject = buildJsonObject {}
        val expectedTool = TextTool(default = "")
        val factory = JsonSchemaTextToolFactory(jsonObject)

        val actualTool = factory.create()

        assertEquals(expectedTool, actualTool)
    }

    @Test
    fun `creates a proper text tool with default value provided`() {
        val jsonObject = buildJsonObject { put("default", "String config value") }
        val expectedTool = TextTool(default = "String config value")
        val factory = JsonSchemaTextToolFactory(jsonObject)

        val actualTool = factory.create()

        assertEquals(expectedTool, actualTool)
    }

    @Test
    fun `creates a proper text tool with hint value provided`() {
        val jsonObject = buildJsonObject {
            put("hint", "Floating point number config value")
        }
        val expectedTool = TextTool(
            default = "",
            hint = "Floating point number config value"
        )
        val factory = JsonSchemaTextToolFactory(jsonObject)

        val actualTool = factory.create()

        assertEquals(expectedTool, actualTool)
    }

    @Test
    fun `creates a proper text tool with default and hint values provided`() {
        val jsonObject = buildJsonObject {
            put("default", "String config value")
            put("hint", "Floating point number config value")
        }
        val expectedTool = TextTool(
            default = "String config value",
            hint = "Floating point number config value"
        )
        val factory = JsonSchemaTextToolFactory(jsonObject)

        val actualTool = factory.create()

        assertEquals(expectedTool, actualTool)
    }
}
