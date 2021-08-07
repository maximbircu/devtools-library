package com.maximbircu.devtools.common

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.createTool
import com.maximbircu.devtools.common.mvp.BaseTest
import com.maximbircu.devtools.common.presentation.tools.group.GroupTool
import com.maximbircu.devtools.common.presentation.tools.text.TextTool
import com.maximbircu.devtools.common.presentation.tools.toggle.ToggleTool
import com.maximbircu.devtools.common.utils.returns
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonObject
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
    fun `returns proper JSON config`() {
        val tools = mapOf<String, DevTool<*>>(
            "first-tool" to createTool<ToggleTool> { ::value returns true },
            "second-tool" to GroupTool(
                mapOf(
                    "first-child-tool" to createTool<TextTool> { ::value returns "First value" },
                    "second-child-tool" to createTool<TextTool> { ::value returns "Second value" }
                )
            )
        )
        val expectedConfigJsonString = buildJsonObject {
            put("first-tool", true)
            putJsonObject("second-tool") {
                put("first-child-tool", "First value")
                put("second-child-tool", "Second value")
            }
        }.toString()
        val storage: DevToolsStorage = DevToolsStorageImpl(tools)

        val configJsonString = storage.getAllConfigAsJson()

        assertEquals(expectedConfigJsonString, configJsonString)
    }

    @Test
    fun `returns proper filtered JSON config`() {
        val tools = mapOf<String, DevTool<*>>(
            "first-tool" to createTool<ToggleTool> {
                ::value returns true
                ::isEnabled returns true
            },
            "second-tool" to GroupTool(
                mapOf(
                    "first-child-tool" to createTool<TextTool> {
                        ::value returns "First value"
                        ::isEnabled returns false
                    },
                    "second-child-tool" to createTool<TextTool> {
                        ::value returns "Second value"
                        ::isEnabled returns false
                    }
                )
            ),
            "third-tool" to GroupTool(
                mapOf(
                    "first-child-tool" to createTool<TextTool> {
                        ::value returns "First value"
                        ::isEnabled returns false
                    },
                    "second-child-tool" to createTool<TextTool> {
                        ::value returns "Second value"
                        ::isEnabled returns true
                    },
                    "third-child-tool" to createTool<TextTool> {
                        ::value returns 3.4
                        ::isEnabled returns true
                    }
                )
            )
        )
        val expectedConfigJsonString = buildJsonObject {
            put("first-tool", true)
            putJsonObject("third-tool") {
                put("second-child-tool", "Second value")
                put("third-child-tool", 3.4)
            }
        }.toString()
        val storage: DevToolsStorage = DevToolsStorageImpl(tools)

        val configJsonString = storage.getAllConfigAsJson { it.isEnabled }

        assertEquals(expectedConfigJsonString, configJsonString)
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
