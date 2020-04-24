package com.maximbircu.devtools.common.core

import com.maximbircu.devtools.common.stores.PreferencesToolStore
import com.maximbircu.devtools.common.stores.PreferencesToolStoreImpl

/**
 * Base model for a dev tool, each dev tool should extend it to be consumable by the library.
 *
 * @property T the type of the configuration value the tool manipulates with
 * @property title tool title displayed in the configuration screen
 * @property description short description of the dev tool configuration value
 * @property canBeDisabled the user can enable/disable the tool if true
 * @property isEnabled true if the tool is enabled and false vice-versa
 */
abstract class DevTool<T : Any>(
    var title: String? = null,
    var description: String = "",
    var canBeDisabled: Boolean = false,
    var defaultEnabledValue: Boolean = true
) {
    private var _key: String? = null

    var key: String
        get() = _key ?: throw NullPointerException("Dev tool key was not set!")
        set(value) {
            _key = value
            restorePersistedState()
        }

    /**
     * The dev tool configuration value at the moment, note that this is kept just in memory and
     * will not survive after [com.maximbircu.devtools.common.DevTools] recreation.
     *
     * You need to call [persistState] in case you need to persist it.
     */
    lateinit var value: T

    var isEnabled: Boolean = defaultEnabledValue

    /**
     * Implements persistence logic of the tool and allows the persistence of the configuration
     * value the tool is manipulating, and its state.
     */
    protected abstract val store: ToolStore<T>

    /**
     * Provides a value which is used as a default config value till it will
     * not be changed by the user in the dev tools screen or using the app startup arguments.
     *
     * Also, this method is used to identify the dev tool configuration value type in some places.
     *
     * @return default configuration value
     */
    abstract fun getDefaultValue(): T

    /**
     * Persists the current in-memory stored configuration values.
     */
    fun persistState() {
        store.value = value
        store.isEnabled = isEnabled
    }

    fun resetToDefault() {
        value = getDefaultValue()
        isEnabled = defaultEnabledValue
    }

    fun restorePersistedState() {
        value = store.value
        isEnabled = store.isEnabled
    }
}

/**
 * A [DevTool] extension which uses [PreferencesToolStoreImpl] to store the tool metadata, and the
 * configuration value it manages.
 *
 * This dev tool could be extended by any other tool model which is storing the config value to
 * the device preferences and have Boolean, String, Float, Int, Double, or Long config value type.
 */
abstract class PreferencesDevTool<T : Any> : DevTool<T>() {
    override val store: ToolStore<T> get() = PreferencesToolStore.create(this)
}
