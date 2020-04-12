package com.maximbircu.devtools.common.presentation.tools.enum

import com.maximbircu.devtools.common.core.mvp.BasePresenter
import com.maximbircu.devtools.common.core.mvp.Presenter

private const val CUSTOM_OPTION = "custom"

interface EnumToolPresenter : Presenter {
    fun onToolBind(tool: EnumTool)
    fun onStoreConfigValue(selectedOption: String)
    fun onOptionSelected(option: String)

    companion object {
        fun create(view: EnumToolView): EnumToolPresenter {
            return EnumToolPresenterImpl(view)
        }
    }
}

private class EnumToolPresenterImpl(
    view: EnumToolView
) : BasePresenter<EnumToolView>(view), EnumToolPresenter {
    private lateinit var tool: EnumTool
    private val selectedValue get() = tool.store.restore()
    private val selectedValueKey get() = tool.getOptionNameForValue(selectedValue) ?: CUSTOM_OPTION

    override fun onToolBind(tool: EnumTool) {
        this.tool = tool
        view.showOptions(getOptions())
        view.setCustomValue(selectedValue)
        view.selectOption(selectedValueKey)
        onOptionSelected(selectedValueKey)
    }

    override fun onStoreConfigValue(selectedOption: String) {
        tool.store.store(getValueForOption(selectedOption))
    }

    override fun onOptionSelected(option: String) {
        if (option == CUSTOM_OPTION) {
            view.showCustomValueInputView()
        } else {
            view.hideCustomValueInputView()
        }
    }

    private fun getValueForOption(option: String): String {
        return if (option == CUSTOM_OPTION) {
            view.customOptionValue
        } else {
            tool.options.getValue(option)
        }
    }

    private fun getOptions(): List<String> {
        val options = tool.options.keys.toMutableList()
        if (tool.allowCustom) {
            options.add(CUSTOM_OPTION)
        }
        return options
    }
}
