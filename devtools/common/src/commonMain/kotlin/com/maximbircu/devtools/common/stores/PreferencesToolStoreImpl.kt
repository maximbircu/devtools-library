package com.maximbircu.devtools.common.stores

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.ToolStore

/**
 * An extension of [ToolStore] which has access to the device preferences and stores all dev tool
 * data to it.
 */
interface PreferencesToolStore<T> : ToolStore<T> {
    companion object {
        /**
         * @param tool the store might use some tool metadata like [DevTool.key] for store actions
         */
        fun <T : Any> create(tool: DevTool<T>): PreferencesToolStore<T> {
            return PreferencesToolStoreImpl(tool)
        }
    }
}

expect open class PreferencesToolStoreImpl<T : Any>(tool: DevTool<T>) : PreferencesToolStore<T>
