package com.maximbircu.devtools.common.presentation.tools.enum.selector

import com.maximbircu.devtools.common.core.mvp.BasePresenter
import com.maximbircu.devtools.common.core.mvp.Presenter
import com.maximbircu.devtools.common.presentation.tools.enum.EnumTool
import com.maximbircu.devtools.common.presentation.tools.enum.EnumToolView

private const val CUSTOM_OPTION = "custom"

interface EnumToolOptionSelectorPresenter : Presenter {
    /**
     * Should be called as soon as a [EnumTool] instance has been provided to the [EnumToolView].
     *
     * @param tool the tool instance provided to the view
     */
    fun onToolBind(tool: EnumTool)

    /**
     * Should be called as soon as a new configuration option is selected.
     *
     * @param option the selected configuration option at the invocation moment
     */
    fun onOptionSelected(option: String)

    /**
     * Should be called whenever the custom value input field text is changed.
     *
     * @param text the custom value input field new text
     */
    fun onCustomValueChanged(text: String)

    companion object {
        /**
         * Provides a new [EnumToolOptionSelectorPresenter] instance.
         *
         * @param view a [EnumToolOptionSelectorView] instance
         * @param onNewOptionSelected should be invoked whenever a new option gets selected
         */
        fun create(
            view: EnumToolOptionSelectorView,
            onNewOptionSelected: (String) -> Unit
        ): EnumToolOptionSelectorPresenter {
            return EnumToolOptionSelectorPresenterImpl(view, onNewOptionSelected)
        }
    }
}

private class EnumToolOptionSelectorPresenterImpl(
    view: EnumToolOptionSelectorView,
    private val onNewOptionSelected: (String) -> Unit
) : BasePresenter<EnumToolOptionSelectorView>(view), EnumToolOptionSelectorPresenter {
    private lateinit var tool: EnumTool

    override fun onToolBind(tool: EnumTool) {
        this.tool = tool
        view.showOptions(getOptions())
        view.setCustomValue(tool.value)
        val selectedValueKey = getOptionNameForValue(tool.value) ?: CUSTOM_OPTION
        view.selectOption(selectedValueKey)
        onOptionSelected(selectedValueKey)
    }

    override fun onOptionSelected(option: String) {
        if (option == CUSTOM_OPTION) {
            view.showCustomValueInputView()
        } else {
            onNewOptionSelected(tool.options.getValue(option))
            view.hideCustomValueInputView()
        }
    }

    override fun onCustomValueChanged(text: String) = onNewOptionSelected(text)

    private fun getOptions(): List<String> {
        val options = tool.options.keys.toMutableList()
        if (tool.allowCustom) {
            options.add(CUSTOM_OPTION)
        }
        return options
    }

    private fun getOptionNameForValue(value: Any): String? {
        return tool.options.keys.firstOrNull { tool.options[it] == value }
    }
}
