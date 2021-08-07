package com.maximbircu.devtools.common.presentation.tools.time

import com.maximbircu.devtools.common.mvp.BaseTest
import kotlin.test.Test
import kotlin.test.assertEquals

class TimeToolTest : BaseTest() {
    @Test
    fun `returns proper default value`() {
        val tool = TimeTool(days = 1, hours = 2, minutes = 3, seconds = 4, milliseconds = 5)

        assertEquals(93784005, tool.getDefaultValue())
    }

    @Test
    fun `uses default values`() {
        val tool = TimeTool()

        assertEquals(0, tool.getDefaultValue())
    }
}
