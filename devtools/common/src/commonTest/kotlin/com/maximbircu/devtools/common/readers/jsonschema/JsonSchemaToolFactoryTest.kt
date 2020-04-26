package com.maximbircu.devtools.common.readers.jsonschema

import com.maximbircu.devtools.common.mvp.BaseTest
import com.maximbircu.devtools.common.presentation.tools.toggle.ToggleTool
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class JsonSchemaToolFactoryTest : BaseTest() {
    @Test
    fun `sets all required base tool properties in tool create`() {
        val jsonObject = json {
            "title" to "Tool title"
            "description" to "Tool description"
            "canBeDisabled" to false
            "isEnabled" to true
            "defaultEnabledValue" to true
            "isCritical" to false
        }
        val factory = JsonSchemaToolFactoryStub(jsonObject)

        val tool = factory.create()

        assertEquals("Tool title", tool.title)
        assertEquals("Tool description", tool.description)
        assertFalse(tool.canBeDisabled)
        assertTrue(tool.defaultEnabledValue)
        assertFalse(tool.isCritical)
    }
}

private class JsonSchemaToolFactoryStub(
    jsonObject: JsonObject
) : JsonSchemaToolFactory<ToggleTool>(jsonObject) {
    override fun createDevTool(): ToggleTool = ToggleTool()
}
