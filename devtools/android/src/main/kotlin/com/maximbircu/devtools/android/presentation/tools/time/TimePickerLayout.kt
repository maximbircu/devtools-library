package com.maximbircu.devtools.android.presentation.tools.time

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.maximbircu.devtools.android.R
import com.maximbircu.devtools.common.presentation.tools.time.Time
import kotlinx.android.synthetic.main.layout_time_picker.view.days
import kotlinx.android.synthetic.main.layout_time_picker.view.hours
import kotlinx.android.synthetic.main.layout_time_picker.view.milliseconds
import kotlinx.android.synthetic.main.layout_time_picker.view.minutes
import kotlinx.android.synthetic.main.layout_time_picker.view.seconds

internal class TimePickerLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    companion object {
        private const val DEFAULT_MAX_DAYS_VALUE: Int = 9999
        private const val DEFAULT_MAX_HOURS_VALUE: Int = 23
        private const val DEFAULT_MAX_MINUTES_VALUE: Int = 59
        private const val DEFAULT_MAX_SECONDS_VALUE: Int = 59
        private const val DEFAULT_MAX_MILLISECONDS_VALUE: Int = 999
    }

    var time
        set(time) {
            days.value = time.days.toInt()
            hours.value = time.hours.toInt()
            minutes.value = time.minutes.toInt()
            seconds.value = time.seconds.toInt()
            milliseconds.value = time.milliseconds.toInt()
        }
        get() = Time(
            days.value.toLong(),
            hours.value.toLong(),
            minutes.value.toLong(),
            seconds.value.toLong(),
            milliseconds.value.toLong()
        )

    init {
        inflate(context, R.layout.layout_time_picker, this)
        days.maxValue = DEFAULT_MAX_DAYS_VALUE
        hours.maxValue = DEFAULT_MAX_HOURS_VALUE
        minutes.maxValue = DEFAULT_MAX_MINUTES_VALUE
        seconds.maxValue = DEFAULT_MAX_SECONDS_VALUE
        milliseconds.maxValue = DEFAULT_MAX_MILLISECONDS_VALUE
    }
}
