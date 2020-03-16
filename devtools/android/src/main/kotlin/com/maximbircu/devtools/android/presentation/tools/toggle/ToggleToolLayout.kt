package com.maximbircu.devtools.android.presentation.tools.toggle

import android.content.Context
import com.maximbircu.devtools.android.R
import com.maximbircu.devtools.android.presentation.tool.DevToolLayout
import com.maximbircu.devtools.common.presentation.tools.toggle.ToggleTool
import com.maximbircu.devtools.common.presentation.tools.toggle.ToggleToolPresenter
import com.maximbircu.devtools.common.presentation.tools.toggle.ToggleToolView
import kotlinx.android.synthetic.main.layout_toggle_tool.view.toggleValue

internal class ToggleToolLayout(context: Context) : DevToolLayout<ToggleTool>(context), ToggleToolView {
    private val presenter = ToggleToolPresenter.create(this)
    override val layoutRes: Int get() = R.layout.layout_toggle_tool

    override fun onBind(tool: ToggleTool) {
        presenter.onBind(tool)
    }

    override fun storeNewConfig() {
        presenter.onConfigUpdate(toggleValue.isChecked)
    }

    override fun setValue(value: Boolean) {
        toggleValue.isChecked = value
    }
}
