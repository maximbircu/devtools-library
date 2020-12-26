package com.maximbircu.devtools.android.presentation.tools.enumtool.selectors.chips

import android.annotation.SuppressLint
import android.content.Context
import android.view.ViewTreeObserver.OnPreDrawListener
import android.widget.FrameLayout
import com.maximbircu.devtools.android.databinding.LayoutEnumToolChipsOptionSelectorBinding
import com.maximbircu.devtools.android.extensions.hide
import com.maximbircu.devtools.android.extensions.inflater
import com.maximbircu.devtools.android.extensions.onTextChanged
import com.maximbircu.devtools.android.extensions.show
import com.maximbircu.devtools.common.presentation.tools.enum.EnumTool
import com.maximbircu.devtools.common.presentation.tools.enum.selector.EnumToolOptionSelectorPresenter
import com.maximbircu.devtools.common.presentation.tools.enum.selector.EnumToolOptionSelectorView

@SuppressLint("ViewConstructor")
class EnumToolChipsOptionSelectorLayout(
    context: Context,
    tool: EnumTool,
    onOptionSelected: (String) -> Unit
) : FrameLayout(context), EnumToolOptionSelectorView {
    private val presenter = EnumToolOptionSelectorPresenter.create(this, onOptionSelected)
    private val binding =
        LayoutEnumToolChipsOptionSelectorBinding.inflate(context.inflater, this, true)

    init {
        presenter.onToolBind(tool)
        scrollToSelectedChipAfterPreDraw()
        binding.chipGroup.setOnCheckedChangeListener(presenter::onOptionSelected)
        binding.customValue.onTextChanged(presenter::onCustomValueChanged)
    }

    override fun showOptions(options: List<String>) = binding.chipGroup.setChips(options)

    override fun selectOption(option: String) = binding.chipGroup.selectChip(option)

    override fun setCustomValue(value: String) = binding.customValue.setText(value)

    override fun showCustomValueInputView() = binding.customValue.show()

    override fun hideCustomValueInputView() = binding.customValue.hide()

    private fun scrollToSelectedChipAfterPreDraw() {
        binding.chipGroup.viewTreeObserver.addOnPreDrawListener(object : OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                val checkedChip = binding.chipGroup.getCheckedChip()
                binding.container.scrollTo(checkedChip.x.toInt(), checkedChip.y.toInt())
                binding.chipGroup.viewTreeObserver.removeOnPreDrawListener(this)
                return true
            }
        })
    }
}
