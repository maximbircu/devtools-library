package com.maximbircu.devtools.common.core

/**
 * Encapsulates the persistence strategy of a dev tool.
 * All dev tools should define their persistence strategy by implementing this interface.
 */
interface ToolStore<T> {
    /**
     * The dev tool enable state to be stored.
     *
     * True if the tool is enabled and to false vice-versa.
     */
    var isEnabled: Boolean

    /**
     * The dev tool configuration value which will be stored.
     */
    var value: T
}
