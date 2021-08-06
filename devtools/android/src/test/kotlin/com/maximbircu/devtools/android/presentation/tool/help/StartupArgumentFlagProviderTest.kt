package com.maximbircu.devtools.android.presentation.tool.help

import com.maximbircu.devtools.android.BaseTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class StartupArgumentFlagProviderTest : BaseTest() {
    @Test
    fun `returns --es startup argument flag for a string value`() {
        val flag = StartupArgumentFlagProvider.getFor("String config value")

        assertEquals("--es", flag)
    }

    @Test
    fun `returns --ez startup argument flag for a boolean value`() {
        val flag = StartupArgumentFlagProvider.getFor(true)

        assertEquals("--ez", flag)
    }

    @Test
    fun `returns --ei startup argument flag for a int value`() {
        val flag = StartupArgumentFlagProvider.getFor(1)

        assertEquals("--ei", flag)
    }

    @Test
    fun `returns --el startup argument flag for a long value`() {
        val flag = StartupArgumentFlagProvider.getFor(1L)

        assertEquals("--el", flag)
    }

    @Test
    fun `returns --ef startup argument flag for a float value`() {
        val flag = StartupArgumentFlagProvider.getFor(1f)

        assertEquals("--ef", flag)
    }

    @Test
    fun `returns --ef startup argument flag for a double value`() {
        val flag = StartupArgumentFlagProvider.getFor(1.0)

        assertEquals("--ef", flag)
    }

    @Test
    fun `throws exception for a unknown startup argument flag`() {
        assertFailsWith<IllegalArgumentException> {
            StartupArgumentFlagProvider.getFor(object {})
        }
    }
}
