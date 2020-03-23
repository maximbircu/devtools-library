package com.maximbircu.devtools.common.stores

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.ToolStore

/**
 * An extension of [ToolStore] which has access to the device preferences and stores all dev tool
 * data to it.
 *
 * @param tool the preference might use some tool metadata like [DevTool.key] for store actions
 */
expect open class PreferencesToolStore<T : Any>(tool: DevTool<T>) : ToolStore<T>
