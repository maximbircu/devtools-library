package com.maximbircu.devtools.customtool

import android.annotation.SuppressLint
import android.content.Context
import com.maximbircu.devtools.SampleApplication
import com.maximbircu.devtools.common.core.ToolStore

private val sharedPrefs = SampleApplication.application.getSharedPreferences(
    "MY_CUSTOM_TOOL_STORE",
    Context.MODE_PRIVATE
)

@SuppressLint("ApplySharedPref")
class MyCustomToolStore(private val tool: MyCustomTool): ToolStore<Boolean> {
    override var isEnabled: Boolean
        get() = sharedPrefs.getBoolean("is_enabled_${tool.key}", tool.defaultEnabledValue)
        set(value) { sharedPrefs.edit().putBoolean("is_enabled_${tool.key}", value).commit() }

    override var value: Boolean
        get() = sharedPrefs.getBoolean(tool.key, tool.getDefaultValue())
        set(value) { sharedPrefs.edit().putBoolean(tool.key, value).commit() }
}
