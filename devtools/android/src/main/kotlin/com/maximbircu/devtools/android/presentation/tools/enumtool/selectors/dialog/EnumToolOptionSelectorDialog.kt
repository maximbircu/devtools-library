package com.maximbircu.devtools.android.presentation.tools.enumtool.selectors.dialog

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.maximbircu.devtools.android.databinding.LayoutEnumToolDialogOptionSelectorBinding
import com.maximbircu.devtools.android.extensions.hide
import com.maximbircu.devtools.android.extensions.inflater
import com.maximbircu.devtools.android.extensions.onTextChanged
import com.maximbircu.devtools.android.extensions.show
import com.maximbircu.devtools.common.presentation.tools.enum.EnumTool
import com.maximbircu.devtools.common.presentation.tools.enum.selector.EnumToolOptionSelectorPresenter
import com.maximbircu.devtools.common.presentation.tools.enum.selector.EnumToolOptionSelectorView

class EnumToolOptionSelectorDialog(
    private val context: Context,
    private val tool: EnumTool,
    onPositiveButtonClick: () -> Unit,
    onNegativeButtonClick: () -> Unit,
    onNewOptionSelected: (String) -> Unit
) : EnumToolOptionSelectorView {
    private val presenter = EnumToolOptionSelectorPresenter.create(this, onNewOptionSelected)
    private val adapter = EnumToolOptionsAdapter(presenter::onOptionSelected)
    private val binding = LayoutEnumToolDialogOptionSelectorBinding.inflate(context.inflater)
    private val dialog = MaterialAlertDialogBuilder(context)
        .setPositiveButton(android.R.string.ok) { _, _ -> onPositiveButtonClick() }
        .setNegativeButton(android.R.string.cancel) { _, _ -> onNegativeButtonClick() }
        .setTitle(tool.title)
        .setView(binding.root)
        .create()

    fun show() {
        dialog.show()
        presenter.onToolBind(tool)
        binding.optionsList.layoutManager = LinearLayoutManager(context)
        binding.optionsList.adapter = adapter
        binding.optionsList.scrollToPosition(adapter.getSelectedOptionPosition())
        binding.customValue.onTextChanged(presenter::onCustomValueChanged)
    }

    override fun showOptions(options: List<String>) = adapter.addOptions(options)

    override fun selectOption(option: String) = adapter.selectOption(option)

    override fun setCustomValue(value: String) = binding.customValue.setText(value)

    override fun showCustomValueInputView() = binding.customValue.show()

    override fun hideCustomValueInputView() = binding.customValue.hide()
}
