package com.maximbircu.devtools.android.presentation.tools.enum

import android.content.Context
import android.view.ViewTreeObserver.OnPreDrawListener
import com.maximbircu.devtools.android.R
import com.maximbircu.devtools.android.extensions.hide
import com.maximbircu.devtools.android.extensions.show
import com.maximbircu.devtools.android.presentation.tool.DevToolLayout
import com.maximbircu.devtools.common.presentation.tools.enum.EnumTool
import com.maximbircu.devtools.common.presentation.tools.enum.EnumToolPresenter
import com.maximbircu.devtools.common.presentation.tools.enum.EnumToolView
import kotlinx.android.synthetic.main.layout_enum_tool.view.chipGroup
import kotlinx.android.synthetic.main.layout_enum_tool.view.container
import kotlinx.android.synthetic.main.layout_enum_tool.view.customValue

class EnumToolLayout(context: Context) : DevToolLayout<EnumTool>(context), EnumToolView {
    private val presenter: EnumToolPresenter = EnumToolPresenter.create(this)
    override val layoutRes: Int get() = R.layout.layout_enum_tool
    override val customOptionValue: String get() = customValue.text.toString()

    override fun onBind(tool: EnumTool) {
        presenter.onToolBind(tool)
        chipGroup.setOnCheckedChangeListener(presenter::onOptionSelected)
        scrollToSelectedChipAterPreDraw()
    }

    override fun storeConfigValue() = presenter.onStoreConfigValue(chipGroup.getCheckedChipData())

    override fun showOptions(options: List<String>) = chipGroup.setChips(options)

    override fun checkOption(option: String) = chipGroup.selectChip(option)

    override fun setCustomValue(optionValue: String) = customValue.setText(optionValue)

    override fun showCustomValueInputView() = customValue.show()

    override fun hideCustomValueInputView() = customValue.hide()

    private fun scrollToSelectedChipAterPreDraw() {
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
