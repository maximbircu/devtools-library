package com.maximbircu.devtools.common.presentation.tools.text

import com.maximbircu.devtools.common.core.mvp.BaseView
import kotlin.reflect.KClass

/**
 * Presents a dev tool which is a ble to manipulate text and number configuration values.
 */
interface TextToolView : BaseView {
    /**
     * Should present a hint which explains what configuration fits proper the dev tool.
     *
     * @param hint the text hint that should be presented to the user
     */
    fun setHint(hint: String?)

    /**
     * Should present the current configuration value.
     *
     * @param value current dev tool configuration value
     */
    fun setTextValue(value: String)

    /**
     * Should set the configuration value input data type.
     *
     * This should be used in case it's possible to add some input limitations for the user.
     * For example exclude letter characters for the keyboard for number configuration values.
     */
    fun setInputDataType(configurationValueType: KClass<*>)
}
