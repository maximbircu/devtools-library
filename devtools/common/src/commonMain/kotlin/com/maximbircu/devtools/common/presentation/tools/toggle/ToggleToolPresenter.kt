package com.maximbircu.devtools.common.presentation.tools.toggle

import com.maximbircu.devtools.common.core.mvp.BasePresenter
import com.maximbircu.devtools.common.core.mvp.Presenter

interface ToggleToolPresenter : Presenter {
    /**
     * Should be called when as soon as a tool object was provided to the view.
     */
    fun onBind(tool: ToggleTool)

    /**
     * Should be called as soon as a configuration update was triggered.
     *
     * Will persist the currently selected value when called.
     *
     * @param data the currently selected value of the toggle
     */
    fun onConfigUpdate(data: Boolean)

    companion object {
        fun create(view: ToggleToolView): ToggleToolPresenter {
            return ToggleToolPresenterImpl(view)
        }
    }
}

private class ToggleToolPresenterImpl(
    view: ToggleToolView
) : BasePresenter<ToggleToolView>(view), ToggleToolPresenter {
    private lateinit var tool: ToggleTool

    override fun onBind(tool: ToggleTool) {
        this.tool = tool
        view.setValue(tool.store.restore())
    }

    override fun onConfigUpdate(data: Boolean) {
        tool.store.store(data)
    }
}
