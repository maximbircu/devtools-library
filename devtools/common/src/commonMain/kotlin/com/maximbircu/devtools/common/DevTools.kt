package com.maximbircu.devtools.common

import com.maximbircu.devtools.common.core.DevTool
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

    fun restorePersistedState()

    /**
     * Updates all dev tools with values taken from [params].
     *
     * @param params a collection of key-value pairs where the key is
     * [com.maximbircu.devtools.common.core.DevTool.key] and the value is the new config value the
     * tool should take
     *
     * Please, note that in case the tool you want to update using this method is a child of a
     * [com.maximbircu.devtools.common.presentation.tools.group.GroupTool] then it's key should
     * be prefixed with its parent key according to the logic the
     * [com.maximbircu.devtools.common.core.DevTool.key] is set, i.e `group-tool-key.tool-to-update`
     * @see DevToolsParser.getDevTools
     */
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
        restorePersistedState()
    }

    override fun updateFromParams(params: Map<String, Any>) {
        if (params.isEmpty()) return
        tools.forEachRecursively { _, tool ->
            params[tool.key]?.let { paramValue ->
                println(paramValue)
                tool.isEnabled = true
                @Suppress("UNCHECKED_CAST")
                (tool as DevTool<Any>).value = paramValue
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

    override fun restorePersistedState() {
        tools.forEachRecursively { _, tool -> tool.restorePersistedState() }
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
