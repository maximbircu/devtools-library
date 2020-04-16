package com.maximbircu.devtools.common.readers.jsonschema

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.mvp.BaseTest
import com.maximbircu.devtools.common.presentation.tools.text.TextTool
import com.maximbircu.devtools.common.presentation.tools.toggle.ToggleTool
import kotlin.test.Test
import kotlin.test.assertEquals

class JsonSchemaReaderTest : BaseTest() {
    @Test
    fun `reads proper dev tools from provided json configuration`() {
        val config = getConfig()
        val reader = JsonSchemaReader(config)
        val expectedTools = mapOf<String, DevTool<*>>(
            "json-schema-toggle-tool" to ToggleTool(defaultValue = true),
            "json-schema-text-tool" to TextTool(defaultValue = "String config value")
        )

        val actualTools = reader.getDevTools()

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
