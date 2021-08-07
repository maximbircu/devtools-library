package com.maximbircu.devtools.common.extensions

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.createTool
import com.maximbircu.devtools.common.presentation.tools.group.GroupTool
import com.maximbircu.devtools.common.presentation.tools.text.TextTool
import com.maximbircu.devtools.common.presentation.tools.toggle.ToggleTool
import com.maximbircu.devtools.common.utils.mockk
import com.maximbircu.devtools.common.utils.returns
import io.mockk.verify
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonObject
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class DevToolExtensionsKtTest {
    @Test
    fun `the action is invoked for each tool`() {
        val listener: (String, DevTool<*>) -> Unit = mockk(relaxed = true)
        val groupTool = GroupTool(mapOf("child-tool" to createTool<TextTool>()))
        val tools = mapOf("first-tool" to ToggleTool(), "second-tool" to groupTool)

        tools.forEachRecursively(listener)

        verify { listener("first-tool", tools.getValue("first-tool")) }
        verify { listener("second-tool", groupTool) }
        verify { listener("child-tool", groupTool.tools.getValue("child-tool")) }
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
        val expectedConfigJsonObject = buildJsonObject {
            put("first-tool", true)
            putJsonObject("second-tool") {
                put("first-child-tool", "First value")
                put("second-child-tool", "Second value")
            }
        }

        val configJsonObject = tools.toJsonObjectOfValues()

        assertEquals(expectedConfigJsonObject, configJsonObject)
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
        val expectedConfigJsonObject = buildJsonObject {
            put("first-tool", true)
            putJsonObject("third-tool") {
                put("second-child-tool", "Second value")
                put("third-child-tool", 3.4)
            }
        }

        val configJsonObject = tools.toJsonObjectOfValues { it.isEnabled }

        assertEquals(expectedConfigJsonObject, configJsonObject)
    }

    @Test
    fun `throws exception when tries to convert to primitive json element a non primitive type`() {
        val tools = mapOf<String, DevTool<*>>(
            "first-tool" to createTool<ToggleTool> { ::value returns object {} }
        )
        assertFailsWith<IllegalArgumentException> { tools.toJsonObjectOfValues() }
    }
}
