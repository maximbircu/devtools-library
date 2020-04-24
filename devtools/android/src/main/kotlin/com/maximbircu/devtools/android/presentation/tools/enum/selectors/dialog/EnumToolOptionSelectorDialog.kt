package com.maximbircu.devtools.android.presentation.tools.enum.selectors.dialog

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.maximbircu.devtools.android.R
import com.maximbircu.devtools.android.extensions.hide
import com.maximbircu.devtools.android.extensions.onTextChanged
import com.maximbircu.devtools.android.extensions.show
import com.maximbircu.devtools.common.presentation.tools.enum.EnumTool
import com.maximbircu.devtools.common.presentation.tools.enum.selector.EnumToolOptionSelectorPresenter
import com.maximbircu.devtools.common.presentation.tools.enum.selector.EnumToolOptionSelectorView
import kotlinx.android.synthetic.main.layout_enum_tool_dialog_option_selector.customValue
import kotlinx.android.synthetic.main.layout_enum_tool_dialog_option_selector.optionsList

class EnumToolOptionSelectorDialog(
    private val context: Context,
    private val tool: EnumTool,
    onPositiveButtonClick: () -> Unit,
    onNegativeButtonClick: () -> Unit,
    onNewOptionSelected: (String) -> Unit
) : EnumToolOptionSelectorView {
    private val presenter = EnumToolOptionSelectorPresenter.create(this, onNewOptionSelected)
    private val adapter = EnumToolOptionsAdapter(presenter::onOptionSelected)
    private val dialog = MaterialAlertDialogBuilder(context)
        .setPositiveButton(android.R.string.ok) { _, _ -> onPositiveButtonClick() }
        .setNegativeButton(android.R.string.cancel) { _, _ -> onNegativeButtonClick() }
        .setTitle(tool.title)
        .setView(R.layout.layout_enum_tool_dialog_option_selector)
        .create()

    fun show() {
        dialog.show()
        presenter.onToolBind(tool)
        dialog.optionsList.layoutManager = LinearLayoutManager(context)
        dialog.optionsList.adapter = adapter
        dialog.optionsList.scrollToPosition(adapter.getSelectedOptionPosition())
        dialog.customValue.onTextChanged(presenter::onCustomValueChanged)
    }

    override fun showOptions(options: List<String>) = adapter.addOptions(options)

    override fun selectOption(option: String) = adapter.selectOption(option)

    override fun setCustomValue(value: String) = dialog.customValue.setText(value)

    override fun showCustomValueInputView() = dialog.customValue.show()

    override fun hideCustomValueInputView() = dialog.customValue.hide()
}
