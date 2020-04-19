package com.maximbircu.devtools.common

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.createTool
import com.maximbircu.devtools.common.mvp.BaseTest
import com.maximbircu.devtools.common.presentation.tools.group.GroupTool
import com.maximbircu.devtools.common.presentation.tools.text.TextTool
import com.maximbircu.devtools.common.presentation.tools.toggle.ToggleTool
import com.maximbircu.devtools.common.utils.returns
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class DevToolsStorageImplTest : BaseTest() {
    @Test
    fun `returns proper configuration value for provided key`() {
        val tools = mapOf<String, DevTool<*>>(
            "first-toggle-tool" to createTool<ToggleTool> { ::value returns false },
            "text-tool" to createTool<TextTool> { ::value returns "Text Value" }
        )

        val storage: DevToolsStorage = DevToolsStorageImpl(tools)

        assertFalse(storage.getValue(key = "first-toggle-tool"))
        assertEquals(storage.getValue(key = "text-tool"), "Text Value")
    }

    @Test
    fun `returns proper enabled value for provided key`() {
        val tools = mapOf<String, DevTool<*>>(
            "first-toggle-tool" to createTool<ToggleTool> { ::isEnabled returns false },
            "text-tool" to createTool<TextTool> { ::isEnabled returns true }
        )

        val storage: DevToolsStorage = DevToolsStorageImpl(tools)

        assertFalse(storage.isEnabled(key = "first-toggle-tool"))
        assertTrue(storage.isEnabled(key = "text-tool"))
    }

    @Test
    fun `returns proper group tool storage for provided key`() {
        val groupToolChildren = mapOf(
            "first-child" to createTool<TextTool>(),
            "second-child" to createTool<ToggleTool>()
        )
        val groupTool: GroupTool = createTool { ::tools returns groupToolChildren }
        val tools = mapOf<String, DevTool<*>>(
            "text-tool" to createTool<TextTool>(),
            "group-tool" to groupTool
        )

        val storage: DevToolsStorage = DevToolsStorageImpl(tools)

        assertEquals(groupToolChildren, storage.getGroup(key = "group-tool").tools)
    }

    @Test
    fun `throws illegal argument exception if there is no tool for provided key`() {
        val tools = mapOf<String, DevTool<Any>>("first-toggle-tool" to createTool())

        val storage: DevToolsStorage = DevToolsStorageImpl(tools)

        assertFailsWith(IllegalArgumentException::class) {
            (storage.getValue(key = "text-tool"))
        }
    }
}
