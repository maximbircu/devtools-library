package com.maximbircu.devtools.common.presentation.tools.enum

import com.maximbircu.devtools.common.core.mvp.BasePresenter
import com.maximbircu.devtools.common.core.mvp.Presenter

private const val CUSTOM_OPTION = "custom"

/**
 * Encapsulates the [EnumTool] business logic and displays enum tool data through [EnumToolView].
 */
interface EnumToolPresenter : Presenter {
    /**
     * Should be called as soon as a [EnumTool] instance has been provided to the [EnumToolView].
     *
     * @param tool the tool instance provided to the view
     */
    fun onToolBind(tool: EnumTool)

    /**
     * Should be called as soon as a configuration update was triggered which happens whenever
     * [com.maximbircu.devtools.common.presentation.tool.DevToolView.persistToolState] gets invoked.
     *
     * Will persist the [selectedOption] when called.
     *
     * @param selectedOption the selected configuration option at the invocation moment
     */
    fun onStoreConfigValue(selectedOption: String)

    /**
     * Should be called as soon as a new configuration option was selected.
     *
     * Will update the custom configuration value input field visibility if needed.
     *
     * @param option the selected configuration option at the invocation moment
     */
    fun onOptionSelected(option: String)

    companion object {
        fun create(view: EnumToolView): EnumToolPresenter {
            /**
             * Provides a new [EnumToolPresenter] instance.
             *
             * @param view a [EnumToolView] instance
             */
            return EnumToolPresenterImpl(view)
        }
    }
}

private class EnumToolPresenterImpl(
    view: EnumToolView
) : BasePresenter<EnumToolView>(view), EnumToolPresenter {
    private lateinit var tool: EnumTool
    private val selectedValue get() = tool.store.restore()
    private val selectedValueKey get() = getOptionNameForValue(selectedValue) ?: CUSTOM_OPTION

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

    private fun getOptionNameForValue(value: Any): String? {
        return tool.options.keys.firstOrNull { tool.options[it] == value }
    }
}
