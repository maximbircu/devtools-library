package com.maximbircu.devtools.common.stores

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.ToolStore
import platform.Foundation.NSUserDefaults
import platform.darwin.NSInteger

actual open class PreferencesToolStore<T> actual constructor(
    private val tool: DevTool<T>
) : ToolStore<T> {
    private val nsUserDefaults: NSUserDefaults = NSUserDefaults("DEV_TOOLS")

    override var isEnabled: Boolean
        set(value) { nsUserDefaults.setBool(value, "${tool.key}_enabled") }
        get() = nsUserDefaults.getOrDefault(
            "${tool.key}_enabled",
            tool.defaultEnabledValue,
            nsUserDefaults::boolForKey
        )

    override fun store(value: T) {
        nsUserDefaults.set(value as Any, tool.key)
    }

    override fun restore(): T {
        return nsUserDefaults.get(tool.key, tool.getDefaultValue() as Any) as T
    }
}

private fun NSUserDefaults.get(key: String, defaultValue: Any): Any {
    return when (defaultValue) {
        is NSInteger -> getOrDefault(key, defaultValue, ::integerForKey)
        is String -> getOrDefault(key, defaultValue, ::objectForKey)!!
        is Float -> getOrDefault(key, defaultValue, ::floatForKey)
        is Boolean -> getOrDefault(key, defaultValue, ::boolForKey)
        is Double -> getOrDefault(key, defaultValue, ::doubleForKey)
        else -> throw IllegalArgumentException("${defaultValue::class} is not supported!")
    }
}

private fun <T> NSUserDefaults.getOrDefault(key: String, default: T, action: (String) -> T): T {
    return if (objectForKey(key) != null) {
        action(key)
    } else {
        default
    }
}

private fun NSUserDefaults.set(value: Any, key: String) {
    when (value) {
        is NSInteger -> setInteger(value, key)
        is String -> setObject(value, key)
        is Float -> setFloat(value, key)
        is Boolean -> setBool(value, key)
        is Double -> setDouble(value, key)
        else -> throw IllegalArgumentException("${value::class} is not supported!")
    }
}
