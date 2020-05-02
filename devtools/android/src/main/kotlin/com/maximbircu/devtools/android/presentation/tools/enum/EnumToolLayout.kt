package com.maximbircu.devtools.android.presentation.tools.enum

import android.content.Context
import com.maximbircu.devtools.android.R
import com.maximbircu.devtools.android.extensions.hide
import com.maximbircu.devtools.android.extensions.setOnClickListener
import com.maximbircu.devtools.android.extensions.show
import com.maximbircu.devtools.android.presentation.tool.DevToolLayout
import com.maximbircu.devtools.android.presentation.tools.enum.selectors.dialog.EnumToolOptionSelectorDialog
import com.maximbircu.devtools.common.presentation.tools.enum.EnumTool
import com.maximbircu.devtools.common.presentation.tools.enum.EnumToolPresenter
import com.maximbircu.devtools.common.presentation.tools.enum.EnumToolView
import kotlinx.android.synthetic.main.layout_dev_tool.view.devToolCard
import kotlinx.android.synthetic.main.layout_enum_tool.view.chipsSelector
import kotlinx.android.synthetic.main.layout_enum_tool.view.currentValue

class EnumToolLayout(context: Context) : DevToolLayout<EnumTool>(context), EnumToolView {
    private val presenter = EnumToolPresenter.create(this)
    override val layoutRes: Int get() = R.layout.layout_enum_tool

    override fun onBind(tool: EnumTool) = presenter.onToolBind(tool)

    override fun showCompactOptionsSelector(tool: EnumTool, onNewOptionSelected: (String) -> Unit) {
        chipsSelector.show()
        chipsSelector.bind(tool, onNewOptionSelected)
    }

    override fun showConfigurationValue(value: String) {
        currentValue.show()
        currentValue.text = value
        devToolCard.setOnClickListener(presenter::onToolClick)
    }

    override fun hideConfigurationValue() {
        currentValue.hide()
    }

    override fun showOptionSelectorDialog(tool: EnumTool, onNewOptionSelected: (String) -> Unit) {
        EnumToolOptionSelectorDialog(
            context,
            tool,
            presenter::onPositiveButtonClick,
            presenter::onNegativeButtonClick,
            onNewOptionSelected
        ).show()
    }

    override fun hideCompactOptionsSelector() {
        chipsSelector.hide()
        devToolCard.setOnClickListener(null)
    }
}
