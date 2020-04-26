package com.maximbircu.devtools.common.extensions

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.createTool
import com.maximbircu.devtools.common.presentation.tools.group.GroupTool
import com.maximbircu.devtools.common.presentation.tools.text.TextTool
import com.maximbircu.devtools.common.presentation.tools.toggle.ToggleTool
import com.maximbircu.devtools.common.utils.mockk
import com.maximbircu.devtools.common.utils.returns
import io.mockk.verify
import kotlin.test.Test
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
    fun `throws exception when tries to convert to primitive json element a non primitive type`() {
        val tools = mapOf<String, DevTool<*>>(
            "first-tool" to createTool<ToggleTool> { ::value returns object {} }
        )
        assertFailsWith<IllegalArgumentException> { tools.toJsonObjectOfValues() }
    }
}
