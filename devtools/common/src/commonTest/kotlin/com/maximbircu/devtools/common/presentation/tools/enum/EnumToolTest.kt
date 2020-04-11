package com.maximbircu.devtools.common.presentation.tools.enum

import com.maximbircu.devtools.common.mvp.BaseTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class EnumToolTest : BaseTest() {
    @Test
    fun `returns proper default value`() {
        val tool = createTool(defaultValueKey = "second-option")

        assertEquals("second-option-value", tool.getDefaultValue())
    }

    @Test
    fun `throws exception if the default value key was not provided`() {
        val tool = createTool(defaultValueKey = null)

        assertFailsWith(NullPointerException::class) { tool.getDefaultValue() }
    }

    @Test
    fun `throws exception if there is no any option for the provided default value key`() {
        val tool = createTool(defaultValueKey = "fourth-option")

        assertFailsWith(IllegalArgumentException::class) { tool.getDefaultValue() }
    }

    @Test
    fun `returns proper option name for value`() {
        val tool = createTool(defaultValueKey = "second-option")

        assertEquals("first-option", tool.getOptionNameForValue("first-option-value"))
        assertEquals("second-option", tool.getOptionNameForValue("second-option-value"))
        assertEquals("third-option", tool.getOptionNameForValue("third-option-value"))
    }

    @Test
    fun `returns null if option is not present`() {
        val tool = createTool(defaultValueKey = "second-option")

        assertEquals(null, tool.getOptionNameForValue("fourth-option-value"))
    }

    private fun createTool(defaultValueKey: String?): EnumTool {
        return EnumTool(defaultValueKey, optionsProvider = StubOptionsProvider())
    }

    private class StubOptionsProvider : EnumOptionsProvider {
        override fun getOptions() = mapOf(
            "first-option" to "first-option-value",
            "second-option" to "second-option-value",
            "third-option" to "third-option-value"
        )
    }
}
