package com.maximbircu.devtools.android.readers

import com.maximbircu.devtools.android.BaseTest
import com.maximbircu.devtools.android.readers.soruces.yaml.YamlDevToolsSource
import com.maximbircu.devtools.android.utils.mockk
import com.maximbircu.devtools.common.readers.DevToolsSources
import org.junit.Test
import java.io.InputStream
import kotlin.test.assertTrue

class DevToolsSourcesKtTest : BaseTest() {
    @Test
    fun `returns an yaml dev tool source given input stream`() {
        val source = DevToolsSources.yaml(mockk(), mockk<InputStream>())

        assertTrue(source is YamlDevToolsSource)
    }

    @Test
    fun `returns an yaml dev tool source given file name`() {
        val source = DevToolsSources.yaml(mockk(relaxed = true), "dev-tools.yml")

        assertTrue(source is YamlDevToolsSource)
    }
}
