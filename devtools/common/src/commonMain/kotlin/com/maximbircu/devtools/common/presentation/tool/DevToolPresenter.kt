package com.maximbircu.devtools.common.presentation.tool

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.mvp.BasePresenter
import com.maximbircu.devtools.common.core.mvp.Presenter

interface DevToolPresenter : Presenter {
    /**
     * Should be called as soon as view receives its tool model.
     */
    fun onToolBind(tool: DevTool<*>)

    /**
     * Should be called whenever the user toggles the tool enable toggle.
     */
    fun onToolEnableToggleUpdated(enabled: Boolean)

    /**
     * Should be called when the view receives the tool persist event.
     */
    fun onPersistToolState()

    companion object {
        fun create(view: DevToolView): DevToolPresenter {
            return DevToolPresenterImpl(view)
        }
    }
}

private class DevToolPresenterImpl(
    view: DevToolView
) : BasePresenter<DevToolView>(view), DevToolPresenter {
    private lateinit var tool: DevTool<*>

    override fun onToolBind(tool: DevTool<*>) {
        this.tool = tool
        setUpToolEnableToggle()
        view.setTitle(tool.title)
        view.setDevToolEnabled(tool.isEnabled)
    }

    override fun onToolEnableToggleUpdated(enabled: Boolean) {
        view.setDevToolEnabled(enabled)
    }

    override fun onPersistToolState() {
        tool.store.isEnabled = view.isToolEnabled
    }

    private fun setUpToolEnableToggle() {
        if (tool.canBeDisabled) {
            view.showEnableToggle()
        } else {
            view.hideEnableToggle()
        }
    }
}
