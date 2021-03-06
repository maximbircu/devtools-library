package com.maximbircu.devtools.common.readers

import com.maximbircu.devtools.common.mvp.BaseTest
import com.maximbircu.devtools.common.readers.sources.JsonSchemaSource
import com.maximbircu.devtools.common.readers.sources.MemoryDevToolsSource
import kotlin.test.Test
import kotlin.test.assertTrue

class DevToolsSourcesTest : BaseTest() {
    @Test
    fun `returns a memory dev tool source`() {
        val source = DevToolsSources.memory(emptyMap())

        assertTrue(source is MemoryDevToolsSource)
    }

    @Test
    fun `returns a json schema dev tool source`() {
        val source = DevToolsSources.json(jsonString = "")

        assertTrue(source is JsonSchemaSource)
    }
}
