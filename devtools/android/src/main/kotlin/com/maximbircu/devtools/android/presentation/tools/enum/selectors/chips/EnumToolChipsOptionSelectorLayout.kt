package com.maximbircu.devtools.android.presentation.tools.enum.selectors.chips

import android.annotation.SuppressLint
import android.content.Context
import android.view.ViewTreeObserver.OnPreDrawListener
import android.widget.FrameLayout
import com.maximbircu.devtools.android.R
import com.maximbircu.devtools.android.extensions.hide
import com.maximbircu.devtools.android.extensions.onTextChanged
import com.maximbircu.devtools.android.extensions.show
import com.maximbircu.devtools.common.presentation.tools.enum.EnumTool
import com.maximbircu.devtools.common.presentation.tools.enum.selector.EnumToolOptionSelectorPresenter
import com.maximbircu.devtools.common.presentation.tools.enum.selector.EnumToolOptionSelectorView
import kotlinx.android.synthetic.main.layout_enum_tool_chips_option_selector.view.chipGroup
import kotlinx.android.synthetic.main.layout_enum_tool_chips_option_selector.view.container
import kotlinx.android.synthetic.main.layout_enum_tool_chips_option_selector.view.customValue

@SuppressLint("ViewConstructor")
class EnumToolChipsOptionSelectorLayout(
    context: Context,
    tool: EnumTool,
    onOptionSelected: (String) -> Unit
) : FrameLayout(context), EnumToolOptionSelectorView {
    private val presenter = EnumToolOptionSelectorPresenter.create(this, onOptionSelected)

    init {
        inflate(context, R.layout.layout_enum_tool_chips_option_selector, this)
        presenter.onToolBind(tool)
        scrollToSelectedChipAfterPreDraw()
        chipGroup.setOnCheckedChangeListener(presenter::onOptionSelected)
        customValue.onTextChanged(presenter::onCustomValueChanged)
    }

    override fun showOptions(options: List<String>) = chipGroup.setChips(options)

    override fun selectOption(option: String) = chipGroup.selectChip(option)

    override fun setCustomValue(value: String) = customValue.setText(value)

    override fun showCustomValueInputView() = customValue.show()

    override fun hideCustomValueInputView() = customValue.hide()

    private fun scrollToSelectedChipAfterPreDraw() {
        chipGroup.viewTreeObserver.addOnPreDrawListener(object : OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                val checkedChip = chipGroup.getCheckedChip()
                container.scrollTo(checkedChip.x.toInt(), checkedChip.y.toInt())
                chipGroup.viewTreeObserver.removeOnPreDrawListener(this)
                return true
            }
        })
    }
}
