package com.maximbircu.devtools.common

import com.maximbircu.devtools.common.core.DevTool

interface DevToolsStorage {
    val tools: Map<String, DevTool<*>>
    fun <T> getValue(key: String): T
    fun isEnabled(key: String): Boolean
}

class DevToolsStorageImpl(
    override val tools: Map<String, DevTool<*>>
) : DevToolsStorage {
    @Suppress("UNCHECKED_CAST")
    override fun <T> getValue(key: String): T = getDevTool(key).store.restore() as T

    override fun isEnabled(key: String): Boolean = getDevTool(key).store.isEnabled

    private fun getDevTool(key: String): DevTool<*> {
        if (key in tools.keys) {
            return tools.getValue(key)
        }
        throw IllegalArgumentException("No tool for $key")
    }
}
