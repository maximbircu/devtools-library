package com.maximbircu.devtools.android.presentation.tool

import android.view.MenuItem
import androidx.annotation.IntegerRes
import com.maximbircu.devtools.android.BaseTest
import com.maximbircu.devtools.android.R
import com.maximbircu.devtools.android.utils.mockk
import com.maximbircu.devtools.common.presentation.tool.DevToolContextMenuPresenter
import io.mockk.every
import io.mockk.verify
import org.junit.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class DevToolContextMenuClickListenerTest : BaseTest() {
    private val presenter: DevToolContextMenuPresenter = mockk()
    private val listener = DevToolContextMenuClickListener(presenter)

    @Test
    fun `notifies about "help" click and consumes the action`() {
        val isActionConsumed = listener.onMenuItemClick(createMenuItem(R.id.help))

        verify { presenter.onHelp() }
        assertTrue(isActionConsumed)
    }

    @Test
    fun `notifies about "select default value" click and consumes the action`() {
        val isActionConsumed = listener.onMenuItemClick(createMenuItem(R.id.select_default_value))

        verify { presenter.onSelectDefaultValue() }
        assertTrue(isActionConsumed)
    }

    @Test
    fun `notifies about "reset changes" click and consumes the action`() {
        val isActionConsumed = listener.onMenuItemClick(createMenuItem(R.id.reset_changes))

        verify { presenter.onResetChanges() }
        assertTrue(isActionConsumed)
    }

    @Test
    fun `throws exception if the menu item is not supported`() {
        assertFailsWith<IllegalArgumentException> {
            listener.onMenuItemClick(createMenuItem(-3))
        }
    }

    private fun createMenuItem(@IntegerRes id: Int) = mockk<MenuItem>().apply {
        every { itemId } returns id
    }
}
