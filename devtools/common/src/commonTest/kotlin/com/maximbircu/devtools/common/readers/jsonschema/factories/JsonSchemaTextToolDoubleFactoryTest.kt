package com.maximbircu.devtools.common.readers.jsonschema.factories

import com.maximbircu.devtools.common.mvp.BaseTest
import com.maximbircu.devtools.common.presentation.tools.text.TextTool
import kotlinx.serialization.json.json
import kotlin.test.Test
import kotlin.test.assertEquals

class JsonSchemaTextToolDoubleFactoryTest : BaseTest() {
    @Test
    fun `creates a proper text tool without default and hint values provided`() {
        val jsonObject = json {}
        val expectedTool = TextTool(defaultValue = 0.0)
        val factory = JsonSchemaTextToolDoubleFactory(jsonObject)

        val actualTool = factory.create()

        assertEquals(expectedTool, actualTool)
    }

    @Test
    fun `creates a proper text tool with default value provided`() {
        val jsonObject = json {
            "default" to 3.4
        }
        val expectedTool = TextTool(defaultValue = 3.4)
        val factory = JsonSchemaTextToolDoubleFactory(jsonObject)

        val actualTool = factory.create()

        assertEquals(expectedTool, actualTool)
    }

    @Test
    fun `creates a proper text tool with hint value provided`() {
        val jsonObject = json {
            "hint" to "Floating point number config value"
        }
        val expectedTool = TextTool(
            defaultValue = 0.0,
            hint = "Floating point number config value"
        )
        val factory = JsonSchemaTextToolDoubleFactory(jsonObject)

        val actualTool = factory.create()

        assertEquals(expectedTool, actualTool)
    }

    @Test
    fun `creates a proper text tool with default and hint values provided`() {
        val jsonObject = json {
            "default" to 3.4
            "hint" to "Floating point number config value"
        }
        val expectedTool = TextTool(
            defaultValue = 3.4,
            hint = "Floating point number config value"
        )
        val factory = JsonSchemaTextToolDoubleFactory(jsonObject)

        val actualTool = factory.create()

        assertEquals(expectedTool, actualTool)
    }
}
