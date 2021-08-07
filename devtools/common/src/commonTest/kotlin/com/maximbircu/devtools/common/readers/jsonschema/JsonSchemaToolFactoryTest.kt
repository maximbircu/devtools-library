package com.maximbircu.devtools.common.readers.jsonschema

import com.maximbircu.devtools.common.mvp.BaseTest
import com.maximbircu.devtools.common.presentation.tools.toggle.ToggleTool
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class JsonSchemaToolFactoryTest : BaseTest() {
    @Test
    fun `sets all required base tool properties in tool create`() {
        val jsonObject = buildJsonObject {
            put("title", "Tool title")
            put("description", "Tool description")
            put("canBeDisabled", false)
            put("isEnabled", true)
            put("defaultEnabledValue", true)
            put("isCritical", false)
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
