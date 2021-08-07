package com.maximbircu.devtools.common.readers.jsonschema.factories

import com.maximbircu.devtools.common.mvp.BaseTest
import com.maximbircu.devtools.common.presentation.tools.text.TextTool
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlin.test.Test
import kotlin.test.assertEquals

class JsonSchemaTextToolIntegerFactoryTest : BaseTest() {
    @Test
    fun `creates a proper text tool without default and hint values provided`() {
        val jsonObject = buildJsonObject {}
        val expectedTool = TextTool(default = 0)
        val factory = JsonSchemaTextToolIntegerFactory(jsonObject)

        val actualTool = factory.create()

        assertEquals(expectedTool, actualTool)
    }

    @Test
    fun `creates a proper text tool with default value provided`() {
        val jsonObject = buildJsonObject {
            put("default", 3)
        }
        val expectedTool = TextTool(default = 3)
        val factory = JsonSchemaTextToolIntegerFactory(jsonObject)

        val actualTool = factory.create()

        assertEquals(expectedTool, actualTool)
    }

    @Test
    fun `creates a proper text tool with hint value provided`() {
        val jsonObject = buildJsonObject {
            put("hint", "Floating point number config value")
        }
        val expectedTool = TextTool(
            default = 0,
            hint = "Floating point number config value"
        )
        val factory = JsonSchemaTextToolIntegerFactory(jsonObject)

        val actualTool = factory.create()

        assertEquals(expectedTool, actualTool)
    }

    @Test
    fun `creates a proper text tool with default and hint values provided`() {
        val jsonObject = buildJsonObject {
            put("default", 3)
            put("hint", "Floating point number config value")
        }
        val expectedTool = TextTool(
            default = 3,
            hint = "Floating point number config value"
        )
        val factory = JsonSchemaTextToolIntegerFactory(jsonObject)

        val actualTool = factory.create()

        assertEquals(expectedTool, actualTool)
    }
}
