package com.maximbircu.devtools.android.presentation.tools.time

import android.content.Context
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.maximbircu.devtools.android.R
import com.maximbircu.devtools.android.presentation.tool.DevToolLayout
import com.maximbircu.devtools.common.presentation.tools.time.Time
import com.maximbircu.devtools.common.presentation.tools.time.TimeTool
import com.maximbircu.devtools.common.presentation.tools.time.TimeToolPresenter
import com.maximbircu.devtools.common.presentation.tools.time.TimeToolView
import kotlinx.android.synthetic.main.dialog_time_selection.view.timePicker
import kotlinx.android.synthetic.main.layout_dev_tool.view.devToolCard
import kotlinx.android.synthetic.main.layout_time_tool.view.timeText

class TimeToolLayout(context: Context) : DevToolLayout<TimeTool>(context), TimeToolView {
    private val presenter: TimeToolPresenter = TimeToolPresenter.create(this)
    private val currentSelectedTime: String get() = timeText.text.toString()
    override val layoutRes: Int get() = R.layout.layout_time_tool

    override fun onBind(tool: TimeTool) {
        presenter.onToolBind(tool)
        devToolCard.setOnClickListener { presenter.onClick(currentSelectedTime) }
    }

    override fun setTime(time: String) {
        timeText.text = time
    }

    override fun showTimePicker(title: String?, time: String) {
        val view = inflate(context, R.layout.dialog_time_selection, null)
        view.timePicker.time = Time(time)
        MaterialAlertDialogBuilder(context)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                presenter.onTimeSelected(view.timePicker.time)
            }
            .setNegativeButton(android.R.string.cancel, null)
            .setView(view)
            .setTitle(title)
            .create()
            .show()
    }
}
