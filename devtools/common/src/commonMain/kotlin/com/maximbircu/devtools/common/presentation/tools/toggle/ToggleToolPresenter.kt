package com.maximbircu.devtools.common.presentation.tools.toggle

import com.maximbircu.devtools.common.core.mvp.BasePresenter
import com.maximbircu.devtools.common.core.mvp.Presenter

/**
 * Encapsulates the [ToggleTool] business logic which stands behind the [ToggleToolView].
 */
interface ToggleToolPresenter : Presenter {
    /**
     * Should be called as soon as a [ToggleTool] instance has been provided
     * to the [ToggleToolView].
     *
     * @param tool the tool instance provided to the view
     */
    fun onToolBind(tool: ToggleTool)

    /**
     * Should be called whenever the toggle value is changed.
     *
     * @param isChecked the new value of the toggle
     */
    fun onCheckedChangeListener(isChecked: Boolean)

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
        view.setValue(tool.value)
    }

    override fun onCheckedChangeListener(isChecked: Boolean) {
        tool.value = isChecked
    }
}
