package com.maximbircu.devtools.common.stores

import android.annotation.SuppressLint
import com.maximbircu.devtools.common.SharedPreferencesProvider
import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.get
import com.maximbircu.devtools.common.put

@SuppressLint("ApplySharedPref")
actual open class PreferencesToolStoreImpl<T : Any> actual constructor(
    private val tool: DevTool<T>
) : PreferencesToolStore<T> {
    private var preferences = SharedPreferencesProvider.getSharedPreferences("DEV_TOOLS")

    override var isEnabled: Boolean
        set(value) { preferences.edit().putBoolean("${tool.key}_enabled", value).commit() }
        get() = preferences.getBoolean("${tool.key}_enabled", tool.defaultEnabledValue)

    override var value: T
        get() = preferences.get(tool.key, tool.getDefaultValue())
        set(value) { preferences.edit().put(tool.key, value).commit() }
}
