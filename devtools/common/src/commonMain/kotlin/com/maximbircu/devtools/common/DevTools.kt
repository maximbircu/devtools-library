package com.maximbircu.devtools.common

import com.maximbircu.devtools.common.core.reader.DevToolsSource
import com.maximbircu.devtools.common.extensions.forEachRecursively

/**
 * This interface serves as a facade for the library. It allows library consumers to manipulate
 * with the devtools states, and the configuration values they hold.
 */
interface DevTools : DevToolsStorage {

    /**
     * This function is invoked whenever a dev tool state is updated.
     * isCriticalUpdate will be true in the case at least one updated tool will be critical.
     * @see [com.maximbircu.devtools.common.core.DevTool.isCritical]
     */
    var onConfigUpdated: (isCriticalUpdate: Boolean) -> Unit

    /**
     * Persists all dev tools configuration changes stored in memory.
     */
    fun persistToolsState()

    fun updateFromParams(params: Map<String, Any>)

    companion object {
        /**
         * Crates a new instance of [DevTools].
         *
         * @param name a unique name that will help to differentiate between similar tools in
         * different dev tools instances
         * @param devToolsSource a collection of sources used by the library to gather dev tools
         * @param onConfigUpdate function to be invoked whenever any config value is updated
         */
        fun create(
            name: String,
            vararg devToolsSource: DevToolsSource,
            onConfigUpdate: (isCriticalUpdate: Boolean) -> Unit = {}
        ): DevTools {
            val parser = DevToolsParser.create(name, devToolsSource.toList())
            val toolsStorage = DevToolsStorageImpl(parser.getDevTools())
            return DevToolsImpl(toolsStorage, onConfigUpdate)
        }
    }
}

private class DevToolsImpl(
    private val toolsStorage: DevToolsStorage,
    override var onConfigUpdated: (isCriticalUpdate: Boolean) -> Unit
) : DevTools, DevToolsStorage by toolsStorage {
    init {
        tools.forEachRecursively { _, tool -> tool.restorePersistedState() }
    }

    override fun updateFromParams(params: Map<String, Any>) {
        if (params.isEmpty()) return
        tools.forEachRecursively { _, tool ->
            params[tool.key]?.let { paramValue ->
                tool.isEnabled = true
                tool.set(paramValue)
            }
        }
        persistToolsState()
    }

    override fun persistToolsState() {
        val (atLeastOneToolWasUpdated, isCriticalUpdate) = persistToolsStateRecursively()
        if (atLeastOneToolWasUpdated) {
            onConfigUpdated(isCriticalUpdate)
        }
    }

    private fun persistToolsStateRecursively(): Pair<Boolean, Boolean> {
        var atLeastOneToolWasUpdated = false
        var isCriticalUpdate = false
        tools.forEachRecursively { _, tool ->
            if (tool.hasUnsavedChanges) {
                tool.persistState()
                atLeastOneToolWasUpdated = true
                if (tool.isCritical) {
                    isCriticalUpdate = true
                }
            }
        }
        return Pair(atLeastOneToolWasUpdated, isCriticalUpdate)
    }
}
