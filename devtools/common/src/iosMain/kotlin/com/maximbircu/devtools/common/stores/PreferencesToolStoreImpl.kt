package com.maximbircu.devtools.common.stores

import com.maximbircu.devtools.common.core.DevTool
import platform.Foundation.NSUserDefaults
import platform.darwin.NSInteger

actual open class PreferencesToolStoreImpl<T : Any> actual constructor(
    private val tool: DevTool<T>
) : PreferencesToolStore<T> {
    private val nsUserDefaults: NSUserDefaults = NSUserDefaults("DEV_TOOLS")

    override var isEnabled: Boolean
        set(value) { nsUserDefaults.setBool(value, "${tool.key}_enabled") }
        get() = nsUserDefaults.getOrDefault(
            "${tool.key}_enabled",
            tool.isEnabled,
            nsUserDefaults::boolForKey
        )

    override var value: T
        get() = nsUserDefaults.get(tool.key, tool.getDefaultValue())
        set(value) { nsUserDefaults.set(value, tool.key) }
}

private fun <T : Any> NSUserDefaults.get(key: String, defaultValue: T): T {
    @Suppress("UNCHECKED_CAST")
    return when (defaultValue) {
        is NSInteger -> getOrDefault(key, defaultValue, ::integerForKey)
        is String -> getOrDefault(key, defaultValue, ::objectForKey)!!
        is Float -> getOrDefault(key, defaultValue, ::floatForKey)
        is Boolean -> getOrDefault(key, defaultValue, ::boolForKey)
        is Double -> getOrDefault(key, defaultValue, ::doubleForKey)
        else -> throw IllegalArgumentException("${defaultValue::class} is not supported!")
    } as T
}

private fun <T : Any> NSUserDefaults.set(value: T, key: String) {
    when (value) {
        is NSInteger -> setInteger(value, key)
        is String -> setObject(value, key)
        is Float -> setFloat(value, key)
        is Boolean -> setBool(value, key)
        is Double -> setDouble(value, key)
        else -> throw IllegalArgumentException("${value::class} is not supported!")
    }
}

private fun <T> NSUserDefaults.getOrDefault(key: String, default: T, action: (String) -> T): T {
    return if (objectForKey(key) != null) {
        action(key)
    } else {
        default
    }
}
