package com.maximbircu.devtools.common.presentation.tools.toggle

import com.maximbircu.devtools.common.core.mvp.BasePresenter
import com.maximbircu.devtools.common.core.mvp.Presenter

/**
 * Encapsulates the logic which stands behind the [ToggleToolView].
 */
interface ToggleToolPresenter : Presenter {
    /**
     * Should be called as soon as a tool object was provided to the view.
     */
    fun onToolBind(tool: ToggleTool)

    /**
     * Should be called as soon as a configuration update was triggered.
     *
     * Will persist the currently selected value when called.
     *
     * @param data the currently selected value of the toggle
     */
    fun onStoreConfigValue(data: Boolean)

    companion object {
        fun create(view: ToggleToolView): ToggleToolPresenter = ToggleToolPresenterImpl(view)
    }
}

private class ToggleToolPresenterImpl(
    view: ToggleToolView
) : BasePresenter<ToggleToolView>(view), ToggleToolPresenter {
    private lateinit var tool: ToggleTool

    override fun onToolBind(tool: ToggleTool) {
        this.tool = tool
        view.setValue(tool.store.restore())
    }

    override fun onStoreConfigValue(data: Boolean) {
        tool.store.store(data)
    }
}
