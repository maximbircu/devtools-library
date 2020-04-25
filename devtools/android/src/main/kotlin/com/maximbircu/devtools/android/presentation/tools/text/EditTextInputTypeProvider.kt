package com.maximbircu.devtools.android.presentation.tools.text

import android.text.InputType.TYPE_CLASS_NUMBER
import android.text.InputType.TYPE_CLASS_TEXT
import android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL
import kotlin.reflect.KClass

internal object EditTextInputTypeProvider {
    fun getInputTypeFor(type: KClass<*>) = when (type) {
        String::class -> TYPE_CLASS_TEXT
        Int::class, Long::class -> TYPE_CLASS_NUMBER
        Float::class, Double::class -> TYPE_CLASS_NUMBER or TYPE_NUMBER_FLAG_DECIMAL
        else -> throw IllegalArgumentException("$type not supported")
    }
}
