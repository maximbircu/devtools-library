package com.maximbircu.devtools.common.stores

import android.annotation.SuppressLint
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import com.maximbircu.devtools.common.application
import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.ToolStore
import java.lang.Double.doubleToRawLongBits
import java.lang.Double.longBitsToDouble

@SuppressLint("ApplySharedPref")
actual open class PreferencesToolStore<T> actual constructor(
    private val tool: DevTool<T>
) : ToolStore<T> {
    private val preferences = application.getSharedPreferences("DEV_TOOLS", MODE_PRIVATE)

    override var isEnabled: Boolean
        set(value) { preferences.edit().putBoolean("${tool.key}_enabled", value).commit() }
        get() = preferences.getBoolean("${tool.key}_enabled", tool.defaultEnabledValue)

    override fun store(value: T) {
        preferences.edit().put(tool.key, value as Any).commit()
    }

    @Suppress("UNCHECKED_CAST")
    override fun restore(): T {
        return preferences.get(tool.key, tool.getDefaultValue() as Any) as T
    }
}

private fun Editor.put(key: String?, value: Any): Editor {
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

private fun SharedPreferences.get(key: String?, defaultValue: Any): Any {
    return when (defaultValue) {
        is Int -> getInt(key, defaultValue)
        is Long -> getLong(key, defaultValue)
        is String -> getString(key, defaultValue) as Any
        is Float -> getFloat(key, defaultValue)
        is Boolean -> getBoolean(key, defaultValue)
        is Double -> getDouble(key, defaultValue)
        else -> throw IllegalArgumentException("${defaultValue::class} is not supported!")
    }
}

private fun Editor.putDouble(key: String?, value: Double): Editor {
    return putLong(key, doubleToRawLongBits(value))
}

internal fun SharedPreferences.getDouble(key: String?, defaultValue: Double): Double {
    return if (contains(key)) {
        longBitsToDouble(all[key] as Long)
    } else {
        defaultValue
    }
}
