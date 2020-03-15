package com.maximbircu.devtools.common

import com.maximbircu.devtools.common.core.reader.DevToolsSource

interface DevTools : DevToolsStorage {
    var onConfigUpdate: () -> Unit

    companion object {
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
) : DevTools, DevToolsStorage by toolsStorage
