package com.maximbircu.devtools.android

import android.os.Bundle
import com.maximbircu.devtools.android.extensions.updateFromBundle
import com.maximbircu.devtools.android.utils.mockk
import com.maximbircu.devtools.common.DevTools
import io.mockk.every
import io.mockk.verify
import org.junit.Test
import kotlin.test.assertFailsWith

class DevToolsExtensionsKtTest : BaseTest() {
    @Test
    fun `extracts the params from bundle properly and updates the dev tools using them`() {
        val devTools: DevTools = mockk()
        val expectedParams = mapOf("toggle-tool" to true, "text-tool" to "Config value")
        val bundle: Bundle = mockk()
        every { bundle.keySet() } returns expectedParams.keys
        every { bundle.get("toggle-tool") } returns expectedParams["toggle-tool"]
        every { bundle.get("text-tool") } returns expectedParams["text-tool"]

        devTools.updateFromBundle(bundle)

        verify { devTools.updateFromParams(expectedParams) }
    }

    @Test
    fun `doesn't update the tools in case the provided bundle is null`() {
        val devTools: DevTools = mockk()

        devTools.updateFromBundle(null)

        verify(exactly = 0) { devTools.updateFromParams(any()) }
    }

    @Test
    fun `throws exception in case the param value was not provided`() {
        val devTools: DevTools = mockk()
        val expectedParams = mapOf("toggle-tool" to true, "text-tool" to null)
        val bundle: Bundle = mockk()
        every { bundle.keySet() } returns expectedParams.keys
        every { bundle.get("toggle-tool") } returns expectedParams["toggle-tool"]
        every { bundle.get("text-tool") } returns expectedParams["text-tool"]

        assertFailsWith<IllegalArgumentException> { devTools.updateFromBundle(bundle) }
        verify(exactly = 0) { devTools.updateFromParams(any()) }
    }
}
