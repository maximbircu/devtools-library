package com.maximbircu.devtools.android.readers

import com.maximbircu.devtools.android.BaseTest
import com.maximbircu.devtools.android.readers.soruces.MemoryDevToolsSource
import com.maximbircu.devtools.common.readers.DevToolsSources
import org.junit.Test
import kotlin.test.assertTrue

class DevToolsSourcesKtTest : BaseTest() {
    @Test
    fun `returns a memory dev tool source`() {
        val source = DevToolsSources.memory(emptyMap())

        assertTrue(source is MemoryDevToolsSource)
    }
}
