package com.maximbircu.devtools.common

import android.content.SharedPreferences
import android.content.SharedPreferences.Editor

internal fun <T : Any> Editor.put(key: String?, value: T): Editor {
    when (value) {
        is Int -> putInt(key, value)
        is Long -> putLong(key, value)
        is String -> putString(key, value)
        is Float -> putFloat(key, value)
        is Boolean -> putBoolean(key, value)
        is Double -> putDouble(key, value)
        else -> throw IllegalArgumentException("${value::class} is not supported!")
    }
    return this
}

internal fun <T : Any> SharedPreferences.get(key: String?, defaultValue: T): T {
    @Suppress("UNCHECKED_CAST")
    return when (defaultValue) {
        is Int -> getInt(key, defaultValue)
        is Long -> getLong(key, defaultValue)
        is String -> getString(key, defaultValue) as Any
        is Float -> getFloat(key, defaultValue)
        is Boolean -> getBoolean(key, defaultValue)
        is Double -> getDouble(key, defaultValue)
        else -> throw IllegalArgumentException("${defaultValue::class} is not supported!")
    } as T
}

internal fun Editor.putDouble(key: String?, value: Double): Editor {
    return putLong(key, java.lang.Double.doubleToRawLongBits(value))
}

internal fun SharedPreferences.getDouble(key: String?, defaultValue: Double) = if (contains(key)) {
    java.lang.Double.longBitsToDouble(all[key] as Long)
} else {
    defaultValue
}
