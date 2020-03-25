package com.maximbircu.devtools.common.core

import com.maximbircu.devtools.common.mvp.BaseTest
import com.maximbircu.devtools.common.stores.PreferencesToolStore
import com.maximbircu.devtools.common.utils.mockk
import io.mockk.every
import io.mockk.mockkObject
import kotlin.test.Test
import kotlin.test.assertTrue

class DevToolTest : BaseTest() {
    @Test
    fun `restores proper enabled value`() {
        val devTool = createDevTool()
        every { devTool.store.isEnabled }.returns(true)

        assertTrue(devTool.isEnabled)
    }

    @Test
    fun `some test`() {
        mockkObject(PreferencesToolStore)
        every { PreferencesToolStore.create<Any>(any()) }.returns(mockk(relaxed = true))

        val devTool = createPreferencesDevTool()

        assertTrue(devTool.store is PreferencesToolStore)
    }

    private fun createPreferencesDevTool(): DevTool<Unit> {
        return object : PreferencesDevTool<Unit>() {
            override fun getDefaultValue() = Unit
        }
    }

    private fun createDevTool(): DevTool<Unit> {
        return object : DevTool<Unit>() {
            override fun getDefaultValue() = Unit
            override val store: ToolStore<Unit> = mockk(relaxed = true)
        }
    }
}
