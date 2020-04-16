package com.maximbircu.devtools.common.readers.sources

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.mvp.BaseTest
import com.maximbircu.devtools.common.presentation.tools.text.TextTool
import com.maximbircu.devtools.common.presentation.tools.toggle.ToggleTool
import com.maximbircu.devtools.common.readers.jsonschema.JsonSchemaReader
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class JsonSchemaSourceTest : BaseTest() {
    @Test
    fun `returns a json schema reader`() {
        val source = JsonSchemaSource(jsonString = "")

        assertTrue(source.getReader() is JsonSchemaReader)
    }

    @Test
    fun `returns proper dev tools`() {
        val config = getConfig()
        val source = JsonSchemaSource(config)
        val expectedTools = mapOf<String, DevTool<*>>(
            "json-schema-toggle-tool" to ToggleTool(defaultValue = true),
            "json-schema-text-tool" to TextTool(defaultValue = "String config value")
        )

        val actualTools = source.getReader().getDevTools()

        assertEquals(expectedTools, actualTools)
    }

    private fun getConfig(): String {
        //language=JSON
        return """
            {
              "type": "object",
              "properties": {
                "json-schema-toggle-tool": {
                  "type": "boolean",
                  "default": true
                },
                "json-schema-text-tool": {
                  "type": "string",
                  "default": "String config value"
                }
              }
            }
          """.trimIndent()
    }
}
