package com.maximbircu.devtools.common.presentation.tools.enum

import com.maximbircu.devtools.common.mvp.BaseTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class EnumToolTest : BaseTest() {
    @Test
    fun `returns proper default value`() {
        val tool = createTool(defaultValueKey = "second-option")

        assertEquals("second-option-value", tool.getDefaultValue())
    }

    @Test
    fun `throws exception if the default value key was not provided`() {
        val tool = EnumTool(optionsProvider = StubOptionsProvider())

        assertFailsWith(NullPointerException::class) { tool.getDefaultValue() }
    }

    @Test
    fun `throws exception if there is no any option for the provided default value key`() {
        val tool = createTool(defaultValueKey = "fourth-option")

        assertFailsWith(IllegalArgumentException::class) { tool.getDefaultValue() }
    }

    @Test
    fun `uses field value in case an options provider was not provided`() {
        val tool = EnumTool()

        assertTrue(tool.options.isEmpty())
    }

    private fun createTool(
        defaultValueKey: String?,
        optionsProvider: EnumOptionsProvider? = StubOptionsProvider()
    ): EnumTool {
        return EnumTool(defaultValueKey, false, optionsProvider)
    }

    private class StubOptionsProvider : EnumOptionsProvider {
        override fun getOptions() = mapOf(
            "first-option" to "first-option-value",
            "second-option" to "second-option-value",
            "third-option" to "third-option-value"
        )
    }
}
