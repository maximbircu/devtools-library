package com.maximbircu.devtools.common.presentation.tool

import com.maximbircu.devtools.common.core.createTool
import com.maximbircu.devtools.common.mvp.BasePresenterTest
import com.maximbircu.devtools.common.presentation.tools.text.TextTool
import com.maximbircu.devtools.common.presentation.tools.toggle.ToggleTool
import com.maximbircu.devtools.common.utils.mockk
import com.maximbircu.devtools.common.utils.returns
import io.mockk.verify
import kotlin.test.Test

class DevToolPresenterImplTest : BasePresenterTest<DevToolView, DevToolPresenter>(mockk()) {
    override fun createPresenter(): DevToolPresenter = DevToolPresenter.create(view)

    @Test
    fun `sets title on tool bind`() {
        val tool: ToggleTool = createTool { ::title returns "Toggle tool title" }

        presenter.onToolBind(tool)

        verify { view.setTitle("Toggle tool title") }
    }

    @Test
    fun `sets tool enabled value on tool bind`() {
        val tool: ToggleTool = createTool { ::isEnabled returns false }

        presenter.onToolBind(tool)

        verify { view.setToolEnableState(isEnabled = false) }
    }

    @Test
    fun `shows enable toggle if tool can be disabled`() {
        val tool: ToggleTool = createTool { ::canBeDisabled returns true }

        presenter.onToolBind(tool)

        verify { view.showEnableToggle() }
    }

    @Test
    fun `hides enable toggle if tool can not be disabled`() {
        val tool: ToggleTool = createTool { ::canBeDisabled returns false }

        presenter.onToolBind(tool)

        verify { view.hideEnableToggle() }
    }

    @Test
    fun `sets tool enabled value on enable toggle update`() {
        presenter.onToolBind(createTool())

        presenter.onToolEnableToggleUpdated(false)

        verify { view.setToolEnableState(isEnabled = false) }
    }

    @Test
    fun `updates tool enabled value on enable toggle update`() {
        val tool: TextTool = createTool()
        presenter.onToolBind(tool)

        presenter.onToolEnableToggleUpdated(true)

        verify { tool.isEnabled = true }
    }
}
