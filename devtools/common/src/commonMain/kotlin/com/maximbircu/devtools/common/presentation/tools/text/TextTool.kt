package com.maximbircu.devtools.common.presentation.tools.text

import com.maximbircu.devtools.common.core.PreferencesDevTool
import kotlin.reflect.KClass

class TextTool(
    private val defaultValue: Any? = null,
    val hint: String? = null
) : PreferencesDevTool<Any>() {
    private val supportedTypes = setOf(
        String::class,
        Int::class,
        Float::class,
        Long::class,
        Double::class
    )

    val dataType: KClass<*>
        get() {
            val defaultValueType = getDefaultValue()::class
            return if (supportedTypes.contains(defaultValueType)) {
                defaultValueType
            } else {
                throw IllegalArgumentException("${defaultValueType.simpleName} type not supported")
            }
        }

    override fun getDefaultValue(): Any {
        return defaultValue ?: throw NullPointerException("Default val required")
    }
}
