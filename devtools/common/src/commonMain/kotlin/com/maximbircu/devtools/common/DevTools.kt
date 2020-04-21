package com.maximbircu.devtools.common

import com.maximbircu.devtools.common.core.reader.DevToolsSource
import com.maximbircu.devtools.common.extensions.forEachRecursively

/**
 * This interface serves as a facade for the library. It allows library consumers to manipulate
 * with the devtools states, and the configuration values they hold.
 */
interface DevTools : DevToolsStorage {

    /**
     * This function is invoked each time any of the configuration values are updated.
     */
    var onConfigUpdate: () -> Unit

    /**
     * Persists all dev tools configuration changes stored in memory.
     */
    fun persistToolsState()

    companion object {
        /**
         * Crates a new instance of [DevTools].
         *
         * @param devToolsSource a collection of sources used by the library to gather dev tools
         * @param onConfigUpdate function to be invoked whenever any config value is updated
         */
        fun create(
            vararg devToolsSource: DevToolsSource,
            onConfigUpdate: () -> Unit = {}
        ): DevTools {
            val parser = DevToolsParser.create(devToolsSource.toList())
            val toolsStorage = DevToolsStorageImpl(parser.getDevTools())
            return DevToolsImpl(toolsStorage, onConfigUpdate)
        }
    }
}

private class DevToolsImpl(
    private val toolsStorage: DevToolsStorage,
    override var onConfigUpdate: () -> Unit
) : DevTools, DevToolsStorage by toolsStorage {
    override fun persistToolsState() {
        tools.forEachRecursively { _, tool -> tool.persistState() }
        onConfigUpdate()
    }
}
