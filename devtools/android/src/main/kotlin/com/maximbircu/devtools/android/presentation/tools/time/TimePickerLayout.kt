package com.maximbircu.devtools.android.presentation.tools.time

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.maximbircu.devtools.android.R
import com.maximbircu.devtools.android.databinding.LayoutTimePickerBinding
import com.maximbircu.devtools.android.extensions.inflater
import com.maximbircu.devtools.common.presentation.tools.time.Time

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
            binding.days.value = time.days.toInt()
            binding.hours.value = time.hours.toInt()
            binding.minutes.value = time.minutes.toInt()
            binding.seconds.value = time.seconds.toInt()
            binding.milliseconds.value = time.milliseconds.toInt()
        }
        get() = Time(
            binding.days.value.toLong(),
            binding.hours.value.toLong(),
            binding.minutes.value.toLong(),
            binding.seconds.value.toLong(),
            binding.milliseconds.value.toLong()
        )

    private val binding = LayoutTimePickerBinding.inflate(context.inflater, this)

    init {
        inflate(context, R.layout.layout_time_picker, this)
        binding.days.maxValue = DEFAULT_MAX_DAYS_VALUE
        binding.hours.maxValue = DEFAULT_MAX_HOURS_VALUE
        binding.minutes.maxValue = DEFAULT_MAX_MINUTES_VALUE
        binding.seconds.maxValue = DEFAULT_MAX_SECONDS_VALUE
        binding.milliseconds.maxValue = DEFAULT_MAX_MILLISECONDS_VALUE
    }
}
