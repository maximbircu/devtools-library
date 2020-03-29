package com.maximbircu.devtools.common.core.reader

import com.maximbircu.devtools.common.core.DevTool

/**
 * Provides devtools objects to the library.
 */
interface DevToolsReader {
    /**
     * @return key value pair collection of devtools
     */
    fun getDevTools(): Map<String, DevTool<*>>
}
