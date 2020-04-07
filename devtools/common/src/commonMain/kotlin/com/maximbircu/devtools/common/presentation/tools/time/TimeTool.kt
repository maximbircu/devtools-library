package com.maximbircu.devtools.common.presentation.tools.time

import com.maximbircu.devtools.common.core.PreferencesDevTool

/**
 * Represents a dev tool able to manipulate time configuration values.
 *
 * @property days time duration in days
 * @property hours time duration in hours
 * @property minutes time duration in minutes
 * @property seconds time duration in seconds
 * @property milliseconds time duration in milliseconds
 */
class TimeTool(
    private val days: Long = 0,
    private val hours: Long = 0,
    private val minutes: Long = 0,
    private val seconds: Long = 0,
    private val milliseconds: Long = 0
) : PreferencesDevTool<Long>() {
    override fun getDefaultValue(): Long {
        return Time(days, hours, minutes, seconds, milliseconds).inMilliseconds()
    }
}
