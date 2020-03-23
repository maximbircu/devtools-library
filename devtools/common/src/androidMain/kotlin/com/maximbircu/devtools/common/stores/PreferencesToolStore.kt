package com.maximbircu.devtools.common.stores

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import com.maximbircu.devtools.common.SharedPreferencesProvider
import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.ToolStore
import java.lang.Double.doubleToRawLongBits
import java.lang.Double.longBitsToDouble

@SuppressLint("ApplySharedPref")
actual open class PreferencesToolStore<T : Any> actual constructor(
    private val tool: DevTool<T>
) : ToolStore<T> {
    private var preferences = SharedPreferencesProvider.getSharedPreferences("DEV_TOOLS")

    override var isEnabled: Boolean
        set(value) { preferences.edit().putBoolean("${tool.key}_enabled", value).commit() }
        get() = preferences.getBoolean("${tool.key}_enabled", tool.defaultEnabledValue)

    override fun store(value: T) {
        preferences.edit().put(tool.key, value).commit()
    }

    override fun restore(): T {
        return preferences.get(tool.key, tool.getDefaultValue())
    }
}

private fun <T : Any> Editor.put(key: String?, value: T): Editor {
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

private fun <T : Any> SharedPreferences.get(key: String?, defaultValue: T): T {
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

private fun Editor.putDouble(key: String?, value: Double): Editor {
    return putLong(key, doubleToRawLongBits(value))
}

internal fun SharedPreferences.getDouble(key: String?, defaultValue: Double) = if (contains(key)) {
    longBitsToDouble(all[key] as Long)
} else {
    defaultValue
}
