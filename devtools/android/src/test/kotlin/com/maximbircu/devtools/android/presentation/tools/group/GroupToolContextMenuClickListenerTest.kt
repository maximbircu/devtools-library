package com.maximbircu.devtools.android.presentation.tools.group

import android.view.MenuItem
import androidx.annotation.IntegerRes
import com.maximbircu.devtools.android.BaseTest
import com.maximbircu.devtools.android.R
import com.maximbircu.devtools.android.utils.mockk
import com.maximbircu.devtools.common.presentation.tools.group.GroupToolContextMenuPresenter
import io.mockk.every
import io.mockk.verify
import org.junit.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class GroupToolContextMenuClickListenerTest : BaseTest() {
    private val presenter: GroupToolContextMenuPresenter = mockk()
    private val listener = GroupToolContextMenuClickListener(presenter)

    @Test
    fun `notifies about "enable all" click and consumes the action`() {
        val isActionConsumed = listener.onMenuItemClick(createMenuItem(R.id.enable_all))

        verify { presenter.onEnableAll() }
        assertTrue(isActionConsumed)
    }

    @Test
    fun `notifies about "disable all" click and consumes the action`() {
        val isActionConsumed = listener.onMenuItemClick(createMenuItem(R.id.disable_all))

        verify { presenter.onDisableAll() }
        assertTrue(isActionConsumed)
    }

    @Test
    fun `notifies about "set all to default" click and consumes the action`() {
        val isActionConsumed = listener.onMenuItemClick(createMenuItem(R.id.set_all_to_default))

        verify { presenter.onSetAllToDefault() }
        assertTrue(isActionConsumed)
    }

    @Test
    fun `notifies about "reset all changes" click and consumes the action`() {
        val isActionConsumed = listener.onMenuItemClick(createMenuItem(R.id.reset_all_changes))

        verify { presenter.onResetAllChanges() }
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
