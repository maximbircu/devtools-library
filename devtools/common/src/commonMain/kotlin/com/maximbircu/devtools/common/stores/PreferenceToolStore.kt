package com.maximbircu.devtools.common.stores

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.ToolStore

expect open class PreferencesToolStore<T>(tool: DevTool<T>) : ToolStore<T>
