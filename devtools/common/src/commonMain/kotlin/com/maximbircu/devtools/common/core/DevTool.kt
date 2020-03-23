package com.maximbircu.devtools.common.core

import com.maximbircu.devtools.common.stores.PreferencesToolStore

/**
 * Base model for a dev tool, each dev tool should extend it to be consumable by the library.
 *
 * @property T the type of the configuration value the tool manipulates with
 * @property title tool title displayed in the configuration screen
 * @property description short description of the dev tool configuration value
 * @property canBeDisabled the user can enable/disable the tool if true
 * @property defaultEnabledValue the default tool enable value if it can be enabled/disabled
 */
abstract class DevTool<T>(
    var title: String? = null,
    var description: String = "",
    var canBeDisabled: Boolean = false,
    var defaultEnabledValue: Boolean = true
) {
    /**
     * @return true if the tool is enabled and false vice-versa
     */
    val isEnabled: Boolean get() = store.isEnabled

    /**
     * Unique dev tool id.
     */
    lateinit var key: String

    /**
     * Implements persistence logic of the tool and allows the persistence of the configuration
     * value the tool is manipulating, and its state.
     */
    abstract val store: ToolStore<T>

    /**
     * Provides a value which is used as a default config value till it will
     * not be changed by the user in the dev tools screen or using the app startup arguments.
     *
     * Also, this method is used to identify the dev tool configuration value type in some places.
     *
     * @return default configuration value
     */
    abstract fun getDefaultValue(): T
}

abstract class PreferencesDevTool<T: Any> : DevTool<T>() {
    override val store: ToolStore<T> get() = PreferencesToolStore(this)
}
