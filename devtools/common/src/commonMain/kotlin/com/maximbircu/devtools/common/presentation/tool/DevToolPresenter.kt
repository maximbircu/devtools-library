package com.maximbircu.devtools.common.presentation.tool

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.mvp.BasePresenter
import com.maximbircu.devtools.common.core.mvp.Presenter

interface DevToolPresenter : Presenter {
    fun onBind(tool: DevTool<*>)
    fun onToolEnableToggleUpdated(enabled: Boolean)
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

    override fun onBind(tool: DevTool<*>) {
        this.tool = tool
        view.setTitle(tool.title)
        setUpToolEnableToggle()
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
