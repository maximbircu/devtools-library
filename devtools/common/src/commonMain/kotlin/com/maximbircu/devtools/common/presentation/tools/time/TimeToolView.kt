package com.maximbircu.devtools.common.presentation.tools.time

import com.maximbircu.devtools.common.core.mvp.BaseView

/**
 * Presents a dev tool which is able to manipulate time configuration values.
 */
interface TimeToolView : BaseView {
    /**
     * Should present the current time configuration value.
     *
     * @param time current time configuration value
     */
    fun setTime(time: String)

    /**
     * Should display a time picker UI component which will allow the user update the time
     * configuration value.
     *
     * @param title time dev tool title
     * @param time current time configuration value
     */
    fun showTimePicker(title: String?, time: String)
}
