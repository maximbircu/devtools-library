package com.maximbircu.devtools.common.core.reader

import com.maximbircu.devtools.common.core.DevTool

interface DevToolsReader {
    fun getDevTools(): Map<String, DevTool<Any>>
}
