package com.maximbircu.devtools.common.presentation.tool

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.mvp.BasePresenter
import com.maximbircu.devtools.common.core.mvp.Presenter

interface DevToolPresenter : Presenter {
    /**
     * Should be called as soon as a [DevTool] instance has been provided
     * to the [DevToolView].
     */
    fun onToolBind(tool: DevTool<*>)

    /**
     * Should be called whenever the user toggles the tool enable toggle.
     */
    fun onToolEnableToggleUpdated(isEnabled: Boolean)

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

    private fun updateToolEnableToggleVisibility() {
        if (tool.canBeDisabled) {
            view.showEnableToggle()
        } else {
            view.hideEnableToggle()
        }
    }
}
