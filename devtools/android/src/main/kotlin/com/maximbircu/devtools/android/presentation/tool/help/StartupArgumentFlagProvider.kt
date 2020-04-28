package com.maximbircu.devtools.android.presentation.tool.help

object StartupArgumentFlagProvider {
    fun getFor(value: Any): String = when (value) {
        is String -> "--es"
        is Boolean -> "--ez"
        is Int -> "--ei"
        is Long -> "--el"
        is Float, is Double -> "--ef"
        else -> throw IllegalArgumentException("$value not supported!")
    }
}
