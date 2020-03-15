package com.maximbircu.devtools.common

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.createTool
import com.maximbircu.devtools.common.mvp.BaseTest
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class DevToolsStorageImplTest : BaseTest() {
    @Test
    fun `returns proper value for provided key`() {
        val tools = mapOf<String, DevTool<Any>>(
            "first-toggle-tool" to createTool { storedData = false },
            "second-toggle-tool" to createTool { storedData = true }
        )

        val storage: DevToolsStorage = DevToolsStorageImpl(tools)

        assertFalse(storage.getValue(key = "first-toggle-tool"))
        assertTrue(storage.getValue(key = "second-toggle-tool"))
    }

    @Test
    fun `returns proper enabled value for provided key`() {
        val tools = mapOf<String, DevTool<Any>>(
            "first-toggle-tool" to createTool { enabled = false },
            "second-toggle-tool" to createTool { enabled = true }
        )

        val storage: DevToolsStorage = DevToolsStorageImpl(tools)

        assertFalse(storage.isEnabled(key = "first-toggle-tool"))
        assertTrue(storage.isEnabled(key = "second-toggle-tool"))
    }

    @Test
    fun `throws illegal argument exception if there is no tool for provided key`() {
        val tools = mapOf<String, DevTool<Any>>(
            "first-toggle-tool" to createTool { enabled = false },
            "second-toggle-tool" to createTool { enabled = true }
        )

        val storage: DevToolsStorage = DevToolsStorageImpl(tools)

        assertFailsWith(IllegalArgumentException::class) {
            (storage.getValue(key = "third-toggle-tool"))
        }
    }
}
