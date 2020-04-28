package com.maximbircu.devtools.common.presentation.tools.text

import com.maximbircu.devtools.common.core.PreferencesDevTool
import kotlin.reflect.KClass

/**
 * Represents a dev tool able to manipulate text and numbers configuration values.
 *
 * @property default the default text or number configuration value
 * @property hint the text which aims to add more context about the purpose of the config value
 */
data class TextTool(
    private val default: Any? = null,
    val hint: String? = null
) : PreferencesDevTool<Any>() {
    private val supportedTypes = setOf(
        String::class,
        Int::class,
        Float::class,
        Long::class,
        Double::class
    )

    /**
     * The configuration value data type. This might be used to check whether the config value
     * is text or number.
     */
    val configValueType: KClass<*> get() = getDefaultValue()::class

    override fun getDefaultValue(): Any {
        val value = default ?: throw NullPointerException("Default val required")
        assertTypeSupported(value::class)
        return value
    }

    private fun assertTypeSupported(type: KClass<*>) {
        if (!supportedTypes.contains(type)) {
            throw IllegalArgumentException("${type.simpleName} type not supported")
        }
    }
}
