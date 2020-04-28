package com.maximbircu.devtools.android.readers

import android.content.res.AssetManager
import com.maximbircu.devtools.android.BaseTest
import com.maximbircu.devtools.android.readers.soruces.yaml.YamlDevToolsSource
import com.maximbircu.devtools.android.utils.mockk
import com.maximbircu.devtools.common.core.reader.DevToolsSource
import com.maximbircu.devtools.common.readers.DevToolsSources
import com.maximbircu.devtools.common.readers.json
import io.mockk.every
import io.mockk.mockkStatic
import org.junit.Test
import java.io.InputStream
import kotlin.test.assertEquals
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

    @Test
    fun `returns a proper json schema source`() {
        mockkStatic("com.maximbircu.devtools.common.readers.DevToolsSourcesKt")
        val assetManager = mockk<AssetManager>(relaxed = true)
        every { assetManager.open(any()) } returns "".byteInputStream()
        val expectedSource: DevToolsSource = mockk()
        every { DevToolsSources.json(any()) } returns expectedSource

        val source = DevToolsSources.json(assetManager, "dev-tools.json")

        assertEquals(expectedSource, source)
    }
}
