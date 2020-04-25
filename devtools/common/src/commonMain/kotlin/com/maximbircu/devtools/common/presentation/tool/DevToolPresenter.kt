package com.maximbircu.devtools.common.presentation.tool

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.mvp.BasePresenter
import com.maximbircu.devtools.common.core.mvp.Presenter

/**
 * Encapsulates common [DevTool] context menu business logic.
 */
interface DevToolContextMenuPresenter : Presenter {
    /**
     * Should be invoked whenever the user intends to open the
     * [com.maximbircu.devtools.common.presentation.tool.help.DevToolHelpDialogView].
     */
    fun onHelp()

    /**
     * Should be invoked whenever the user intends to set the tool default state.
     */
    fun onSelectDefaultValue()

    /**
     * Should be invoked whenever the user intends to reset the changes he made to the tool state.
     */
    fun onResetChanges()
}

/**
 * Encapsulates common [DevTool] business logic which stands behind the [DevToolView].
 */
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

    /**
     * Should be invoked whenever the user wants to open the context menu and clicks on the context
     * menu button.
     */
    fun onContextMenuButtonClick()

    companion object {
        /**
         * Provides a [DevToolPresenter] instance which encapsulates the presentation and user
         * interaction logic of [DevToolView].
         *
         * @param view the [DevToolView] instance which will be used by the presenter
         */
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

    override fun onResetChanges() {
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
