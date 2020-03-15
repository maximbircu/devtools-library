package com.maximbircu.devtools.common.presentation.tool

import com.maximbircu.devtools.common.core.createTool
import com.maximbircu.devtools.common.mvp.BasePresenterTest
import com.maximbircu.devtools.common.presentation.tools.toggle.ToggleTool
import com.maximbircu.devtools.common.utils.mockk
import io.mockk.every
import io.mockk.verify
import kotlin.test.Test

class DevToolPresenterImplTest : BasePresenterTest<DevToolView, DevToolPresenter>(mockk()) {
    override fun createPresenter(): DevToolPresenter = DevToolPresenter.create(view)

    @Test
    fun `sets title on bind`() {
        val tool: ToggleTool = createTool { title = "Toggle tool title" }

        presenter.onBind(tool)

        verify { view.setTitle("Toggle tool title") }
    }

    @Test
    fun `shows enable toggle if tool can be disabled on bind`() {
        val tool: ToggleTool = createTool { canBeDisabled = true }

        presenter.onBind(tool)

        verify { view.showEnableToggle() }
    }

    @Test
    fun `hides enable toggle if tool can not be disabled on bind`() {
        val tool: ToggleTool = createTool { canBeDisabled = false }

        presenter.onBind(tool)

        verify { view.hideEnableToggle() }
    }

    @Test
    fun `sets tool enabled value on bind`() {
        val tool: ToggleTool = createTool { enabled = false }

        presenter.onBind(tool)

        verify { view.setDevToolEnabled(isEnabled = false) }
    }

    @Test
    fun `sets tool enabled value on tool enable toggle updated`() {
        presenter.onToolEnableToggleUpdated(false)

        verify { view.setDevToolEnabled(isEnabled = false) }
    }

    @Test
    fun `stores new tool enabled value on config updated`() {
        val tool: ToggleTool = createTool { enabled = false }
        presenter.onBind(tool)
        every { view.isToolEnabled }.returns(false)

        presenter.onConfigUpdate()

        verify { tool.store.isEnabled = false }
    }
}
