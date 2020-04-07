package com.maximbircu.devtools.common.presentation.tools.time

import com.maximbircu.devtools.common.core.mvp.BasePresenter
import com.maximbircu.devtools.common.core.mvp.Presenter
import com.maximbircu.devtools.common.presentation.tools.text.TextTool
import com.maximbircu.devtools.common.presentation.tools.text.TextToolPresenter
import com.maximbircu.devtools.common.presentation.tools.text.TextToolView

/**
 * Encapsulates the [TimeTool] business logic and displays time tool data through [TimeToolView].
 */
interface TimeToolPresenter : Presenter {
    /**
     * Should be called as soon as a [TimeTool] instance has been provided to the [TimeToolView].
     *
     * @param tool the tool instance provided to the view
     */
    fun onToolBind(tool: TimeTool)

    /**
     * Should be called as soon as a configuration update was triggered which happens whenever
     * [com.maximbircu.devtools.common.presentation.tool.DevToolView.persistToolState] gets invoked.
     *
     * Will persist the [selectedTime] when called.
     *
     * @param selectedTime the time onfiguration value presented at the invocation moment
     */
    fun onStoreConfigValue(selectedTime: String)

    /**
     * Should be called when the user selects the time tool UI element to update the time
     * configuration value.
     *
     * @param selectedTime the time configuration value presented at the invocation moment
     */
    fun onClick(selectedTime: String)

    /**
     * Should be called as soon as a new time was selected.
     *
     * @param time the time configuration value presented at the invocation moment
     */
    fun onTimeSelected(time: Time)

    companion object {
        /**
         * Provides a new [TimeToolPresenter] instance.
         *
         * @param view a [TimeToolView] instance
         */
        fun create(view: TimeToolView): TimeToolPresenter = TimeToolPresenterImpl(view)
    }
}

private class TimeToolPresenterImpl(
    view: TimeToolView
) : BasePresenter<TimeToolView>(view), TimeToolPresenter {
    private lateinit var tool: TimeTool

    override fun onToolBind(tool: TimeTool) {
        this.tool = tool
        view.setTime(Time(tool.store.restore()).toString())
    }

    override fun onTimeSelected(time: Time) {
        view.setTime(time.toString())
    }

    override fun onClick(selectedTime: String) {
        view.displayTimeSelectionDialog(tool.title, selectedTime)
    }

    override fun onStoreConfigValue(selectedTime: String) {
        val newTime = Time(selectedTime).inMilliseconds()
        tool.store.store(newTime)
    }
}
