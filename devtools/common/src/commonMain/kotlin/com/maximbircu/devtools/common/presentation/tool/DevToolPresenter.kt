package com.maximbircu.devtools.common.presentation.tool

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.mvp.BasePresenter
import com.maximbircu.devtools.common.core.mvp.Presenter

interface DevToolContextMenuPresenter : Presenter {
    fun onHelp()
    fun onSelectDefaultValue()
    fun onResetSelection()
}

interface DevToolPresenter : DevToolContextMenuPresenter {
    /**
     * Should be called as soon as a [DevTool] instance has been provided
     * to the [DevToolView].
     */
    fun onToolBind(tool: DevTool<*>)

    /**
     * Should be called whenever the user toggles the tool enable toggle.
     */
    fun onToolEnableToggleUpdated(isEnabled: Boolean)

    fun onContextMenuButtonClick()

    companion object {
        fun create(view: DevToolView): DevToolPresenter = DevToolPresenterImpl(view)
    }
}

private class DevToolPresenterImpl(
    view: DevToolView
) : BasePresenter<DevToolView>(view), DevToolPresenter {
    private lateinit var tool: DevTool<*>

    override fun onToolBind(tool: DevTool<*>) {
        this.tool = tool
        view.setTitle(tool.title)
        view.setToolEnableState(tool.isEnabled)
        updateToolEnableToggleVisibility()
    }

    override fun onToolEnableToggleUpdated(isEnabled: Boolean) {
        tool.isEnabled = isEnabled
        view.setToolEnableState(isEnabled)
    }

    override fun onContextMenuButtonClick() {
        view.showToolContextMenu()
    }

    override fun onHelp() {
        view.showHelpDialog(tool)
    }

    override fun onSelectDefaultValue() {
        tool.resetToDefault()
        view.refreshToolData(tool)
    }

    override fun onResetSelection() {
        tool.restorePersistedState()
        view.refreshToolData(tool)
    }

    private fun updateToolEnableToggleVisibility() {
        if (tool.canBeDisabled) {
            view.showEnableToggle()
        } else {
            view.hideEnableToggle()
        }
    }
}
