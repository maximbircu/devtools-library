package com.maximbircu.devtools.common.readers.jsonschema.factories

import com.maximbircu.devtools.common.mvp.BaseTest
import com.maximbircu.devtools.common.presentation.tools.text.TextTool
import kotlinx.serialization.json.json
import kotlin.test.Test
import kotlin.test.assertEquals

class JsonSchemaTextToolFactoryTest : BaseTest() {
    @Test
    fun `creates a proper text tool without default and hint values provided`() {
        val jsonObject = json {}
        val expectedTool = TextTool(defaultValue = "")
        val factory = JsonSchemaTextToolFactory(jsonObject)

        val actualTool = factory.create()

        assertEquals(expectedTool, actualTool)
    }

    @Test
    fun `creates a proper text tool with default value provided`() {
        val jsonObject = json {
            "default" to "String config value"
        }
        val expectedTool = TextTool(defaultValue = "String config value")
        val factory = JsonSchemaTextToolFactory(jsonObject)

        val actualTool = factory.create()

        assertEquals(expectedTool, actualTool)
    }

    @Test
    fun `creates a proper text tool with hint value provided`() {
        val jsonObject = json {
            "hint" to "Floating point number config value"
        }
        val expectedTool = TextTool(
            defaultValue = "",
            hint = "Floating point number config value"
        )
        val factory = JsonSchemaTextToolFactory(jsonObject)

        val actualTool = factory.create()

        assertEquals(expectedTool, actualTool)
    }

    @Test
    fun `creates a proper text tool with default and hint values provided`() {
        val jsonObject = json {
            "default" to "String config value"
            "hint" to "Floating point number config value"
        }
        val expectedTool = TextTool(
            defaultValue = "String config value",
            hint = "Floating point number config value"
        )
        val factory = JsonSchemaTextToolFactory(jsonObject)

        val actualTool = factory.create()

        assertEquals(expectedTool, actualTool)
    }
}
