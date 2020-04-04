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

    val dataType: KClass<*> get() = getDefaultValue()::class

    override fun getDefaultValue(): Any {
        val value = defaultValue ?: throw NullPointerException("Default val required")
        assertTypeSupported(value::class)
        return value
    }

    private fun assertTypeSupported(type: KClass<*>) {
        if (!supportedTypes.contains(type)) {
            throw IllegalArgumentException("${type.simpleName} type not supported")
        }
    }
}
