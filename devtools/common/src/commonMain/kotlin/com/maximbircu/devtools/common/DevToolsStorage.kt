package com.maximbircu.devtools.common

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.extensions.toJsonObjectOfValues
import com.maximbircu.devtools.common.presentation.tools.group.GroupTool

/**
 * Enables library consumers to be able to read configuration values.
 */
interface DevToolsStorage {
    /**
     * A collection of dev tool objects [DevToolsStorage] holds.
     */
    val tools: Map<String, DevTool<*>>

    /**
     * Provides the current configuration value of the [DevTool] with [key].
     *
     * @param key the dev tool unique id [DevTool.key]
     * @return [T] configuration value of the dev tool with [key]
     */
    fun <T> getValue(key: String): T

    /**
     * Provides all configuration values as JSON string.
     *
     * @param filter (predicate) will be invoked for each [DevTool] to decide whether to add
     * the config to the final JSON object result or not.
     */
    fun getAllConfigAsJson(filter: (tool: DevTool<*>) -> Boolean = { true }): String

    /**
     * Provides a [DevToolsStorage] which contains all group member tools.
     *
     * @param key the group unique id [GroupTool.key]
     * @return a [DevToolsStorage] which contains the [GroupTool.tools] data
     */
    fun getGroup(key: String): DevToolsStorage

    /**
     * Provides information about weather the dev tool with the given [key] is enabled or not by
     * checking [DevTool.key].
     *
     * @param key the dev tool unique id [DevTool.key]
     * @return true in case the dev tool is enabled and false vice-versa.
     */
    fun isEnabled(key: String): Boolean
}

class DevToolsStorageImpl(
    override val tools: Map<String, DevTool<*>>
) : DevToolsStorage {
    @Suppress("UNCHECKED_CAST")
    override fun <T> getValue(key: String): T = getDevTool(key).value as T

    override fun isEnabled(key: String): Boolean = getDevTool(key).isEnabled

    override fun getGroup(key: String): DevToolsStorage {
        return DevToolsStorageImpl((getDevTool(key) as GroupTool).tools)
    }

    override fun getAllConfigAsJson(filter: (tool: DevTool<*>) -> Boolean): String {
        return tools.toJsonObjectOfValues(filter).toString()
    }

    private fun getDevTool(key: String): DevTool<*> {
        if (key in tools.keys) {
            return tools.getValue(key)
        }
        throw IllegalArgumentException("No tool for $key")
    }
}
