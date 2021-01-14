package com.maximbircu.devtools.android.presentation.tools.time

import android.content.Context
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.maximbircu.devtools.android.R
import com.maximbircu.devtools.android.databinding.DialogTimeSelectionBinding
import com.maximbircu.devtools.android.databinding.LayoutTimeToolBinding
import com.maximbircu.devtools.android.extensions.inflater
import com.maximbircu.devtools.android.presentation.tool.DevToolLayout
import com.maximbircu.devtools.common.presentation.tools.time.Time
import com.maximbircu.devtools.common.presentation.tools.time.TimeTool
import com.maximbircu.devtools.common.presentation.tools.time.TimeToolPresenter
import com.maximbircu.devtools.common.presentation.tools.time.TimeToolView

class TimeToolLayout(context: Context) : DevToolLayout<TimeTool>(context), TimeToolView {
    private val presenter: TimeToolPresenter = TimeToolPresenter.create(this)
    private val currentSelectedTime: String
        get() = timeToolBinding.timeText.text.toString()
    private val timeToolBinding = LayoutTimeToolBinding.bind(this)
    override val layoutRes: Int get() = R.layout.layout_time_tool

    override fun onBind(tool: TimeTool) {
        presenter.onToolBind(tool)
        binding.devToolCard.setOnClickListener { presenter.onClick(currentSelectedTime) }
    }

    override fun setTime(time: String) {
        timeToolBinding.timeText.text = time
    }

    override fun showTimePicker(title: String?, time: String) {
        val view = DialogTimeSelectionBinding.inflate(context.inflater)
        view.timePicker.time = Time(time)
        MaterialAlertDialogBuilder(context)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                presenter.onTimeSelected(view.timePicker.time)
            }
            .setNegativeButton(android.R.string.cancel, null)
            .setView(view.root)
            .setTitle(title)
            .create()
            .show()
    }
}
