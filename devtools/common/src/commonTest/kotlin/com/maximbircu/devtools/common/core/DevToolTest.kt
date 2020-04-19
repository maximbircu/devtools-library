package com.maximbircu.devtools.common.core

import com.maximbircu.devtools.common.mvp.BaseTest
import com.maximbircu.devtools.common.utils.mockk
import io.mockk.every
import io.mockk.verify
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse

class DevToolTest : BaseTest() {
    private val store: ToolStore<String> = mockk(relaxed = true)

    @Test
    fun `restores proper persisted state when tool key updated`() {
        val devTool = createDevTool(store)
        every { store.isEnabled } returns false
        every { store.value } returns "Configuration value"

        devTool.key = "some-key"

        assertFalse(devTool.isEnabled)
        assertEquals("Configuration value", devTool.value)
    }

    @Test
    fun `persists dev tool state`() {
        val devTool = createDevTool(store)
        devTool.isEnabled = false
        devTool.value = "Configuration value"

        devTool.persistState()

        verify { store.isEnabled = false }
        verify { store.value = "Configuration value" }
    }

    @Test
    fun `throws exception when trying to access tool key if it was not set`() {
        val devTool = createDevTool(store)

        assertFailsWith(NullPointerException::class) { devTool.key }
    }

    private fun createDevTool(store: ToolStore<String>): DevTool<String> {
        return object : DevTool<String>() {
            override val store: ToolStore<String> = store

            override fun getDefaultValue() = ""
        }
    }
}
