package com.maximbircu.devtools.common.core

/**
 * Encapsulates the persistence strategy of a dev tool.
 * All dev tools should define their persistence strategy by implementing this interface.
 */
interface ToolStore<T> {
    /**
     * True if the tool is enabled and to false vice-versa.
     */
    var isEnabled: Boolean


    /**
     * This method is called when a config value of a dev tool should be persisted.
     *
     * @param value configuration value which should be persisted
     */
    fun store(value: T)

    /**
     * This method provides the persisted value to the library.
     *
     * @return configuration value persisted by the [store] method
     */
    fun restore(): T
}
