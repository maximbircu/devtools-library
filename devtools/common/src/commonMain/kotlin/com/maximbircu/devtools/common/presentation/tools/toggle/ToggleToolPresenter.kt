package com.maximbircu.devtools.common.presentation.tools.toggle

import com.maximbircu.devtools.common.core.mvp.BasePresenter
import com.maximbircu.devtools.common.core.mvp.Presenter

/**
 * Encapsulates the [ToggleTool] business logic which stands behind the [ToggleToolView].
 */
interface ToggleToolPresenter : Presenter {
    /**
     * Should be called as soon as a [ToggleTool] instance was provided to [ToggleToolView].
     *
     * @param tool the tool instance provided to the view
     */
    fun onToolBind(tool: ToggleTool)

    /**
     * Should be called as soon as a configuration update was triggered which happens whenever
     * [com.maximbircu.devtools.common.presentation.tool.DevToolView.persistToolState] gets invoked.
     *
     * Will persist the [value] when called.
     *
     * @param value the configuration value presented at the invocation moment
     */
    fun onStoreConfigValue(value: Boolean)

    companion object {
        /**
         * Provides a new [ToggleToolPresenter] instance.
         *
         * @param view a [ToggleToolView] instance
         */
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

    override fun onStoreConfigValue(value: Boolean) {
        tool.store.store(value)
    }
}
