package com.maximbircu.devtools.common.presentation.tools.toggle

import com.maximbircu.devtools.common.mvp.BaseTest
import kotlin.test.Test
import kotlin.test.assertTrue

class ToggleToolTest : BaseTest() {
    @Test
    fun `returns proper default value`() {
        val tool = ToggleTool(defaultValue = true)

        assertTrue(tool.getDefaultValue())
    }
}
