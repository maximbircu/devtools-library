package com.maximbircu.devtools.common.readers.jsonschema.factories

import com.maximbircu.devtools.common.mvp.BaseTest
import com.maximbircu.devtools.common.presentation.tools.toggle.ToggleTool
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlin.test.Test
import kotlin.test.assertEquals

class JsonSchemaToggleToolFactoryTest : BaseTest() {
    @Test
    fun `creates a proper enum dev tool without default value provided`() {
        val jsonObject = buildJsonObject { }
        val expectedTool = ToggleTool()
        val factory = JsonSchemaToggleToolFactory(jsonObject)

        val actualTool = factory.create()

        assertEquals(expectedTool, actualTool)
    }

    @Test
    fun `creates a proper enum dev tool with default value provided`() {
        val jsonObject = buildJsonObject { put("default", true) }
        val expectedTool = ToggleTool(default = true)
        val factory = JsonSchemaToggleToolFactory(jsonObject)

        val actualTool = factory.create()

        assertEquals(expectedTool, actualTool)
    }
}
