package com.maximbircu.devtools.common.presentation.tools.time

import com.maximbircu.devtools.common.core.mvp.BasePresenter
import com.maximbircu.devtools.common.core.mvp.Presenter

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
     * Should be called when the user selects the time tool UI element to update the time
     * configuration value.
     *
     * @param selectedTime the time configuration value presented at the invocation moment
     */
    fun onClick(selectedTime: String)

    /**
     * Should be called whenever a new time is selected.
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
        view.setTime(Time(tool.value).toString())
    }

    override fun onTimeSelected(time: Time) {
        view.setTime(time.toString())
        tool.value = time.inMilliseconds()
    }

    override fun onClick(selectedTime: String) {
        view.showTimePicker(tool.title, selectedTime)
    }
}
