package com.maximbircu.devtools.common

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.maximbircu.devtools.common.mvp.BaseTest
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.verify
import org.junit.Test
import kotlin.test.assertEquals

class SharedPreferencesProviderTest : BaseTest() {
    @Test
    fun `returns proper shared preferences`() {
        mockkObject(SharedPreferencesProvider)
        val expectedSharedPrefs: SharedPreferences = mockk(relaxed = true)
        val context: Context = mockk(relaxed = true)
        every { context.getSharedPreferences(any(), any()) } returns expectedSharedPrefs
        every {
            SharedPreferencesProvider invokeNoArgs "initAndGetAppCtxWithReflection"
        } returns context

        val actualSharedPrefs = SharedPreferencesProvider.getSharedPreferences("DevTools")

        assertEquals(expectedSharedPrefs, actualSharedPrefs)
        verify { context.getSharedPreferences("DevTools", MODE_PRIVATE) }
    }
}
