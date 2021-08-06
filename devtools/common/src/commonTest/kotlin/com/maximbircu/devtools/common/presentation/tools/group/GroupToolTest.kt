package com.maximbircu.devtools.common.presentation.tools.group

import com.maximbircu.devtools.common.core.createTool
import com.maximbircu.devtools.common.mvp.BaseTest
import com.maximbircu.devtools.common.presentation.tools.toggle.ToggleTool
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class GroupToolTest : BaseTest() {
    @Test
    fun `throws exception if there was no tools provided`() {
        val tool = GroupTool(mapOf())

        assertFailsWith(IllegalArgumentException::class) { tool.getDefaultValue() }
    }

    @Test
    fun `doesn't throw exception if the group has at least one tool`() {
        GroupTool(mapOf("some-tool" to createTool<ToggleTool> { })).getDefaultValue()
    }

    @Test
    fun `uses empty map as default value`() {
        assertTrue(GroupTool().tools.isEmpty())
    }
}
