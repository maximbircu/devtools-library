package com.maximbircu.devtools.common.presentation.tools.enum.selector

import com.maximbircu.devtools.common.core.mvp.BaseView

/**
 * Presents an enum dev tool option selector.
 *
 * Should present a group of single-choice selectable and react on user selection interactions.
 */
interface EnumToolOptionSelectorView : BaseView {
    /**
     * Should present the list of the tool supported configuration options as
     * a single choice UI component.
     *
     * @param options a collection of options to be presented to the user
     */
    fun showOptions(options: List<String>)

    /**
     * Should mark the option as selected.
     *
     * @param option the configuration option to be marked as selected.
     */
    fun selectOption(option: String)

    /**
     * Should set the provided configuration value as text inside the custom configuration value
     * input field.
     *
     * @param value the configuration value in text form
     */
    fun setCustomValue(value: String)

    /**
     * Should show the custom configuration value input field to the user.
     */
    fun showCustomValueInputView()

    /**
     * Should hide the custom configuration value input field.
     */
    fun hideCustomValueInputView()
}
