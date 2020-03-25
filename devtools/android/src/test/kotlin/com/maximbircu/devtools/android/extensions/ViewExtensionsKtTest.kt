package com.maximbircu.devtools.android.extensions

import android.view.View
import com.maximbircu.devtools.android.BaseTest
import com.maximbircu.devtools.android.utils.mockk
import io.mockk.verify
import org.junit.Test

class ViewExtensionsKtTest : BaseTest() {
    @Test
    fun `shows view`() {
        val view: View = mockk()

        view.show()

        verify { view.visibility = View.VISIBLE }
    }

    @Test
    fun `hides view`() {
        val view: View = mockk()

        view.hide()

        verify { view.visibility = View.GONE }
    }

    @Test
    fun `sets click listener`() {
        val view: View = mockk()
        val listener = {}

        view.setOnClickListener(listener)

        verify { view.setOnClickListener(any()) }
    }
}
