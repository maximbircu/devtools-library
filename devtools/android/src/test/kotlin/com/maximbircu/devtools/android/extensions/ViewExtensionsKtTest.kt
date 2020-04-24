package com.maximbircu.devtools.android.extensions

import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import com.maximbircu.devtools.android.BaseTest
import com.maximbircu.devtools.android.utils.mockk
import io.mockk.every
import io.mockk.slot
import io.mockk.verify
import org.junit.Test

class ViewExtensionsKtTest : BaseTest() {
    @Test
    fun `sets view visibility to visible`() {
        val view: View = mockk()

        view.show()

        verify { view.visibility = View.VISIBLE }
    }

    @Test
    fun `sets view visibility to gone`() {
        val view: View = mockk()

        view.hide()

        verify { view.visibility = View.GONE }
    }

    @Test
    fun `sets view visibility to invisible`() {
        val view: View = mockk()

        view.makeInvisible()

        verify { view.visibility = View.INVISIBLE }
    }

    @Test
    @Suppress("UNCHECKED_CAST")
    fun `sets click listener`() {
        val view: View = mockk()
        val slot = slot<OnClickListener>()
        every { view.setOnClickListener(capture(slot)) } answers { slot.captured.onClick(mockk()) }
        val listener = {}

        view.setOnClickListener(listener)

        verify {
            view.setOnClickListener(any())
            listener.invoke()
        }
    }

    @Test
    fun `sets enabled all views recursively`() {
        val children = listOf<View>(createViewGroup(), mockk())
        val rootView = createViewGroup(children)

        rootView.setEnabledRecursively(false)

        verify { rootView.isEnabled = false }
        children.forEach { verify { it.isEnabled = false } }
    }

    private fun createViewGroup(views: List<View> = emptyList()): ViewGroup {
        val view = mockk<ViewGroup>(relaxed = true)
        every { view.childCount } returns views.size
        views.forEachIndexed { index, child -> every { view.getChildAt(index) } returns child }
        return view
    }
}
