package com.maximbircu.devtools.common.presentation.tools.enum

/**
 * Decouples the [EnumTool] from the configuration options source impl.
 *
 * Provide your custom configuration options source implementations to [EnumTool] by implementing
 * this interface and passing the instance to the [EnumTool.optionsProvider].
 */
interface EnumOptionsProvider {
    /**
     * Provides configuration options to [EnumTool].
     *
     * @return a collection of key-value pairs where the key is the option name
     */
    fun getOptions(): Map<String, String>
}
