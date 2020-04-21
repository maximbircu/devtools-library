package com.maximbircu.devtools.common.extensions

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.presentation.tools.group.GroupTool

internal fun Map<String, DevTool<*>>.forEachRecursively(action: (String, DevTool<*>) -> Unit) {
    forEach { (key, tool) ->
        if (tool is GroupTool) {
            tool.tools.forEachRecursively(action)
        }
        action(key, tool)
    }
}
