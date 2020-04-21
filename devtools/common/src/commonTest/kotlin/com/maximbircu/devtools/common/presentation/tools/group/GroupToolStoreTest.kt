package com.maximbircu.devtools.common.presentation.tools.group

import com.maximbircu.devtools.common.mvp.BaseTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GroupToolStoreTest : BaseTest() {
    @Test
    fun `returns always true as tool enabled value`() {
        val store = GroupToolStore()

        assertTrue(store.isEnabled)
    }

    @Test
    fun `returns always Unit as configuration value`() {
        val store = GroupToolStore()

        assertEquals(Unit, store.value)
    }
}
