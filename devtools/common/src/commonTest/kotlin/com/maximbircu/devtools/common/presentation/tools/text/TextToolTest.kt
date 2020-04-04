package com.maximbircu.devtools.common.presentation.tools.text

import com.maximbircu.devtools.common.mvp.BaseTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class TextToolTest : BaseTest() {
    @Test
    fun `returns proper default value`() {
        val tool = TextTool(defaultValue = 3)

        assertEquals(3, tool.getDefaultValue())
    }

    @Test
    fun `returns proper hint value`() {
        val tool = TextTool(defaultValue = 3, hint =  "hint value")

        assertEquals("hint value", tool.hint)
    }

    @Test
    fun `throws exception if value was not provided`() {
        val tool = TextTool()

        assertFailsWith<NullPointerException> { tool.getDefaultValue() }
    }

    @Test
    fun `throws exception if provided value type is not supported`() {
        val tool = TextTool(defaultValue = object {})

        assertFailsWith<IllegalArgumentException> { tool.getDefaultValue() }
    }

    @Test
    fun `returns proper data type`() {
        val tool = TextTool(defaultValue = "text value")

        assertEquals(String::class, tool.dataType)
    }
}
