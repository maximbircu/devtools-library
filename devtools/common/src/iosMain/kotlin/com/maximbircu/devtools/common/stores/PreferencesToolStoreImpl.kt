package com.maximbircu.devtools.common.stores

import com.maximbircu.devtools.common.core.DevTool
import platform.Foundation.NSUserDefaults
import platform.darwin.NSInteger

actual open class PreferencesToolStoreImpl<T : Any> actual constructor(
    private val tool: DevTool<T>
) : PreferencesToolStore<T> {
    private val nsUserDefaults: NSUserDefaults = NSUserDefaults("DEV_TOOLS_${tool.containerName}")

    override var isEnabled: Boolean
        set(value) { nsUserDefaults.setBool(value, "${tool.key}_enabled") }
        get() = nsUserDefaults.get("${tool.key}_enabled", tool.defaultEnabledValue)

    override var value: T
        get() = nsUserDefaults.get(tool.key, tool.getDefaultValue())
        set(value) { nsUserDefaults.set(value, tool.key) }
}

private fun <T : Any> NSUserDefaults.get(key: String, defaultValue: T): T {
    return if (objectForKey(key) != null) {
        @Suppress("UNCHECKED_CAST")
        when (defaultValue) {
            is NSInteger -> integerForKey(key)
            is String -> objectForKey(key)
            is Float -> floatForKey(key)
            is Boolean -> boolForKey(key)
            is Double -> doubleForKey(key)
            else -> throw IllegalArgumentException("${defaultValue::class} is not supported!")
        }
    } else {
        defaultValue
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
