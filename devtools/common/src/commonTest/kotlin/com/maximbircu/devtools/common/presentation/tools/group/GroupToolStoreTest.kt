package com.maximbircu.devtools.common.presentation.tools.group

import com.maximbircu.devtools.common.mvp.BaseTest
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class GroupToolStoreTest : BaseTest() {
    @Test
    fun `returns always true for tool enabled value`() {
        val store = GroupToolStore()

        assertTrue(store.isEnabled)
    }

    @Test
    fun `throws exception when trying to store something`() {
        val store = GroupToolStore()

        assertFailsWith(UnsupportedOperationException::class) { store.store(Unit) }
    }

    @Test
    fun `throws exception when trying to restore something`() {
        val store = GroupToolStore()

        assertFailsWith(UnsupportedOperationException::class) { store.restore() }
    }
}
