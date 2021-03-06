package com.maximbircu.devtools.android.presentation.tools.toggle

import android.content.Context
import com.maximbircu.devtools.android.R
import com.maximbircu.devtools.android.databinding.LayoutToggleToolBinding
import com.maximbircu.devtools.android.presentation.tool.DevToolLayout
import com.maximbircu.devtools.common.presentation.tools.toggle.ToggleTool
import com.maximbircu.devtools.common.presentation.tools.toggle.ToggleToolPresenter
import com.maximbircu.devtools.common.presentation.tools.toggle.ToggleToolView

internal class ToggleToolLayout(
    context: Context
) : DevToolLayout<ToggleTool>(context), ToggleToolView {
    private val presenter = ToggleToolPresenter.create(this)
    private val toggleToolBinding = LayoutToggleToolBinding.bind(this)
    override val layoutRes: Int get() = R.layout.layout_toggle_tool

    override fun onBind(tool: ToggleTool) {
        presenter.onToolBind(tool)
        toggleToolBinding.toggleValue.setOnCheckedChangeListener { _, isChecked ->
            presenter.onCheckedChangeListener(isChecked)
        }
    }

    override fun setValue(value: Boolean) {
        toggleToolBinding.toggleValue.isChecked = value
    }
}
