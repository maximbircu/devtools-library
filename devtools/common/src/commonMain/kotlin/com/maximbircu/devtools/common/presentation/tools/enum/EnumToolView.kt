package com.maximbircu.devtools.common.presentation.tools.enum

import com.maximbircu.devtools.common.core.mvp.BaseView

/**
 * Presents a dev tool which is a ble to manipulate enum configuration values.
 */
interface EnumToolView : BaseView {
    /**
     * Should provide the value of the custom option input field at the invocation moment.
     */
    val customOptionValue: String

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
