package com.maximbircu.devtools.common.presentation.tools.group

import com.maximbircu.devtools.common.mvp.BaseTest
import kotlin.test.Test
import kotlin.test.assertFailsWith

class GroupToolTest : BaseTest() {
    @Test
    fun `throws exception if there was no tools provided`() {
        val tool = GroupTool(mapOf())

        assertFailsWith(IllegalArgumentException::class) { tool.getDefaultValue() }
    }
}
