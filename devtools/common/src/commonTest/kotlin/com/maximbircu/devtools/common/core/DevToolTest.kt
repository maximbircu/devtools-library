package com.maximbircu.devtools.common.core

import com.maximbircu.devtools.common.mvp.BaseTest
import com.maximbircu.devtools.common.utils.mockk
import io.mockk.every
import io.mockk.verify
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class DevToolTest : BaseTest() {
    private val store: ToolStore<String> = mockk(relaxed = true)

    @Test
    fun `persists dev tool state`() {
        val devTool = createDevTool()
        devTool.isEnabled = false
        devTool.value = "Configuration value"

        devTool.persistState()

        verify { store.isEnabled = false }
        verify { store.value = "Configuration value" }
    }

    @Test
    fun `resets the tool state to proper default values`() {
        val devTool = createDevTool(
            default = "Default configuration value",
            defaultEnabledValue = true
        )
        devTool.value = "Some custom value"
        devTool.isEnabled = false

        devTool.resetToDefault()

        assertTrue(devTool.isEnabled)
        assertEquals("Default configuration value", devTool.value)
    }

    // region Has unsaved changes

    @Test
    fun `returns false in case there are no unsaved changes`() {
        val devTool = createDevTool()
        every { store.isEnabled } returns false
        every { store.value } returns "Configuration value"
        devTool.restorePersistedState()

        assertFalse(devTool.hasUnsavedChanges)
    }

    @Test
    fun `returns true in case there the config value was changed`() {
        val devTool = createDevTool()
        every { store.isEnabled } returns false
        every { store.value } returns "Configuration value"
        devTool.restorePersistedState()

        devTool.isEnabled = false
        devTool.value = "New configuration value"

        assertTrue(devTool.hasUnsavedChanges)
    }

    @Test
    fun `returns true in case there the tool enable state was changed`() {
        val devTool = createDevTool()
        every { store.isEnabled } returns false
        every { store.value } returns "Configuration value"

        devTool.isEnabled = true
        devTool.value = "Configuration value"

        assertTrue(devTool.hasUnsavedChanges)
    }

    @Test
    fun `returns true in case both config value and tool enable state was changed`() {
        val devTool = createDevTool()
        every { store.isEnabled } returns false
        every { store.value } returns "Configuration value"
        devTool.restorePersistedState()

        devTool.isEnabled = true
        devTool.value = "New configuration value"

        assertTrue(devTool.hasUnsavedChanges)
    }

    // endregion

    @Test
    fun `throws exception when trying to access tool key if it was not set`() {
        val devTool = createDevTool()

        assertFailsWith(NullPointerException::class) { devTool.key }
    }

    @Test
    fun `returns tool key if it was set`() {
        val devTool = createDevTool()

        devTool.key = "group-tool.toggle-tool"

        assertEquals("group-tool.toggle-tool", devTool.key)
    }

    @Test
    fun `throws exception when trying to access tool containerName if it was not set`() {
        val devTool = createDevTool()

        assertFailsWith(NullPointerException::class) { devTool.containerName }
    }

    @Test
    fun `returns tool container if it was set`() {
        val devTool = createDevTool()

        devTool.containerName = "TEST"

        assertEquals("TEST", devTool.containerName)
    }

    private fun createDevTool(
        store: ToolStore<String> = this.store,
        default: String = "",
        defaultEnabledValue: Boolean = true
    ): DevTool<String> {
        return object : DevTool<String>(defaultEnabledValue = defaultEnabledValue) {
            override val store: ToolStore<String> = store

            override fun getDefaultValue() = default
        }
    }
}
