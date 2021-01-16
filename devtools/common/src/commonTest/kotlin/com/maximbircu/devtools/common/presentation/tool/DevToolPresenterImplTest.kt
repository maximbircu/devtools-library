package com.maximbircu.devtools.common.presentation.tool

import com.maximbircu.devtools.common.core.createTool
import com.maximbircu.devtools.common.mvp.BasePresenterTest
import com.maximbircu.devtools.common.presentation.tools.group.GroupTool
import com.maximbircu.devtools.common.presentation.tools.text.TextTool
import com.maximbircu.devtools.common.presentation.tools.toggle.ToggleTool
import com.maximbircu.devtools.common.utils.mockk
import com.maximbircu.devtools.common.utils.returns
import io.mockk.clearAllMocks
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
    fun `hides enable toggle for a group tool even if it can be disabled `() {
        val tool: GroupTool = createTool { ::canBeDisabled returns true }

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

    @Test
    fun `enables disabled tool when user tries to update it and the tool can be enabled`() {
        val tool: TextTool = createTool {
            ::isEnabled returns false
            ::canBeDisabled returns true
        }
        presenter.onToolBind(tool)

        presenter.onAttemptToEditToolConfigValue()

        verify { tool.isEnabled = true }
        verify { view.setToolEnableState(true) }
    }

    @Test
    fun `doesn't enable enabled tool when user tires to update it`() {
        val tool: TextTool = createTool {
            ::isEnabled returns true
            ::canBeDisabled returns true
        }
        presenter.onToolBind(tool)
        clearAllMocks(recordedCalls = true)

        presenter.onAttemptToEditToolConfigValue()

        verify(exactly = 0) { tool.isEnabled = true }
        verify(exactly = 0) { view.setToolEnableState(true) }
    }

    @Test
    fun `doesn't enable a tool that can not be enabled when user tires to update it`() {
        val tool: TextTool = createTool {
            ::isEnabled returns true
            ::canBeDisabled returns false
        }
        presenter.onToolBind(tool)
        clearAllMocks(recordedCalls = true)

        presenter.onAttemptToEditToolConfigValue()

        verify(exactly = 0) { tool.isEnabled = true }
        verify(exactly = 0) { view.setToolEnableState(true) }
    }

    @Test
    fun `doesn't enable a disabled tool that can not be enabled when user tires to update it`() {
        val tool: TextTool = createTool {
            ::isEnabled returns false
            ::canBeDisabled returns false
        }
        presenter.onToolBind(tool)
        clearAllMocks(recordedCalls = true)

        presenter.onAttemptToEditToolConfigValue()

        verify(exactly = 0) { tool.isEnabled = true }
        verify(exactly = 0) { view.setToolEnableState(true) }
    }

    // region Tool Context Menu

    @Test
    fun `shows context menu on context menu button click`() {
        presenter.onContextMenuButtonClick()

        verify { view.showToolContextMenu() }
    }

    @Test
    fun `shows help dialog on help click`() {
        val tool: TextTool = createTool()
        presenter.onToolBind(tool)

        presenter.onHelp()

        verify { view.showHelpDialog(tool) }
    }

    @Test
    fun `resets tool to default value on select default value`() {
        val tool: TextTool = createTool()
        presenter.onToolBind(tool)

        presenter.onSelectDefaultValue()

        verify { tool.resetToDefault() }
    }

    @Test
    fun `refreshes presented tool data on select default value`() {
        val tool: TextTool = createTool()
        presenter.onToolBind(tool)

        presenter.onSelectDefaultValue()

        verify { view.refreshToolData(tool) }
    }

    @Test
    fun `restores persisted tool values on reset changes`() {
        val tool: TextTool = createTool()
        presenter.onToolBind(tool)

        presenter.onResetChanges()

        verify { tool.restorePersistedState() }
    }

    @Test
    fun `refreshes presented tool data on reset changes`() {
        val tool: TextTool = createTool()
        presenter.onToolBind(tool)

        presenter.onResetChanges()

        verify { view.refreshToolData(tool) }
    }

    // endregion
}
