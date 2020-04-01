package com.maximbircu.devtools.common.stores

import android.content.SharedPreferences
import com.maximbircu.devtools.common.SharedPreferencesProvider
import com.maximbircu.devtools.common.mvp.BaseTest
import com.maximbircu.devtools.common.presentation.tools.toggle.ToggleTool
import com.maximbircu.devtools.common.utils.mockk
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.verify
import org.junit.Assert.assertTrue
import org.junit.Test

class PreferencesToolStoreImplTest : BaseTest() {
    private val sharedPrefsEditor = mockk<SharedPreferences.Editor>(relaxed = true)
    private val sharedPrefs = mockk<SharedPreferences>()

    init {
        every { sharedPrefsEditor.putBoolean(any(), any()) } returns sharedPrefsEditor
        every { sharedPrefs.edit() } returns sharedPrefsEditor
        mockkObject(SharedPreferencesProvider)
        every { SharedPreferencesProvider.getSharedPreferences(any()) } returns sharedPrefs
    }

    @Test
    fun `stores tool enabled value`() {
        val preferencesStore = createPreferencesStore(toolKey = "radio-tool")

        preferencesStore.isEnabled = true

        verify { sharedPrefsEditor.putBoolean("radio-tool_enabled", true) }
    }

    @Test
    fun `restores tool enabled value`() {
        val preferencesStore = createPreferencesStore(toolKey = "radio-tool")
        every { sharedPrefs.getBoolean("radio-tool_enabled", false) } returns true

        assertTrue(preferencesStore.isEnabled)
    }

    @Test
    fun `stores tool config value`() {
        val preferencesStore = createPreferencesStore(toolKey = "toggle-tool")

        preferencesStore.store(true)

        verify { sharedPrefsEditor.putBoolean("toggle-tool", true) }
    }

    @Test
    fun `restores tool config value`() {
        val preferencesStore = createPreferencesStore(toolKey = "toggle-tool")
        every { sharedPrefs.getBoolean("toggle-tool", false) } returns true

        assertTrue(preferencesStore.restore())
    }

    private fun createPreferencesStore(
        toolKey: String = "toggle_tool"
    ): PreferencesToolStoreImpl<Boolean> = PreferencesToolStoreImpl(
        mockk<ToggleTool>(relaxed = true).apply { every { key } returns toolKey }
    )
}
