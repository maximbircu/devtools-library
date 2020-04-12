package com.maximbircu.devtools.common.presentation.tools.enum

import com.maximbircu.devtools.common.core.mvp.BaseView

interface EnumToolView : BaseView {
    val customOptionValue: String

    fun showOptions(options: List<String>)
    fun selectOption(option: String)

    fun setCustomValue(optionValue: String)
    fun showCustomValueInputView()
    fun hideCustomValueInputView()
}
