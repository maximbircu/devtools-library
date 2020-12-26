package com.maximbircu.devtools.android.presentation.tools.enum

import android.content.Context
import android.view.View
import android.widget.TextView
import com.maximbircu.devtools.android.R
import com.maximbircu.devtools.android.databinding.LayoutEnumToolBinding
import com.maximbircu.devtools.android.extensions.setOnClickListener
import com.maximbircu.devtools.android.presentation.tool.DevToolLayout
import com.maximbircu.devtools.android.presentation.tools.enum.selectors.chips.EnumToolChipsOptionSelectorLayout
import com.maximbircu.devtools.android.presentation.tools.enum.selectors.dialog.EnumToolOptionSelectorDialog
import com.maximbircu.devtools.common.presentation.tools.enum.EnumTool
import com.maximbircu.devtools.common.presentation.tools.enum.EnumToolPresenter
import com.maximbircu.devtools.common.presentation.tools.enum.EnumToolView

class EnumToolLayout(context: Context) : DevToolLayout<EnumTool>(context), EnumToolView {
    private val presenter = EnumToolPresenter.create(this)
    private val enumToolBinding = LayoutEnumToolBinding.bind(this)
    override val layoutRes: Int get() = R.layout.layout_enum_tool

    override fun onBind(tool: EnumTool) = presenter.onToolBind(tool)

    override fun showCompactOptionsSelector(tool: EnumTool, onNewOptionSelected: (String) -> Unit) {
        setContentView(EnumToolChipsOptionSelectorLayout(context, tool, onNewOptionSelected))
    }

    override fun showConfigurationValue(value: String) {
        setContentView(TextView(context).apply { text = value })
        binding.devToolCard.setOnClickListener(presenter::onToolClick)
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

    private fun setContentView(contentView: View) {
        enumToolBinding.contentContainer.removeAllViews()
        enumToolBinding.contentContainer.addView(contentView)
    }
}
