package com.maximbircu.devtools.common.readers.jsonschema.factories

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.createTool
import com.maximbircu.devtools.common.mvp.BaseTest
import com.maximbircu.devtools.common.readers.jsonschema.JsonSchemaToolFactory
import com.maximbircu.devtools.common.utils.mockk
import io.mockk.every
import kotlinx.serialization.json.json
import kotlin.test.Test
import kotlin.test.assertEquals

class JsonSchemaGroupToolFactoryTest : BaseTest() {
    @Test
    fun `creates a proper group dev tool`() {
        val expectedTools = mapOf<String, DevTool<*>>(
            "first-tool" to createTool(),
            "second-tool" to createTool()
        )
        val properties = expectedTools.map { (key, value) -> key to createSchemaToolFactory(value) }
        val factory = JsonSchemaGroupToolFactory(json { }, properties.toMap())

        val tool = factory.create()

        assertEquals(expectedTools, tool.tools)
    }

    private fun createSchemaToolFactory(tool: DevTool<*>): JsonSchemaToolFactory<*> {
        return mockk<JsonSchemaToolFactory<*>>().apply { every { create() } returns tool }
    }
}
