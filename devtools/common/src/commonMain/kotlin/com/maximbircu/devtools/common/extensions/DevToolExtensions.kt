package com.maximbircu.devtools.common.extensions

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.presentation.tools.group.GroupTool
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

internal fun Map<String, DevTool<*>>.forEachRecursively(action: (String, DevTool<*>) -> Unit) {
    forEach { (key, tool) ->
        if (tool is GroupTool) {
            tool.tools.forEachRecursively(action)
        }
        action(key, tool)
    }
}

@Suppress("LongMethod")
internal fun Map<String, DevTool<*>>.toJsonObjectOfValues(
    predicate: (tool: DevTool<*>) -> Boolean = { true }
): JsonObject {
    val configValues = mutableMapOf<String, JsonElement>()
    forEach { (key, tool) ->
        if (tool is GroupTool) {
            val childToolsValues = tool.tools.toJsonObjectOfValues(predicate)
            if (childToolsValues.isNotEmpty()) {
                configValues[key] = childToolsValues
            }
        } else {
            if (predicate(tool)) {
                configValues[key] = tool.value.toJsonPrimitive()
            }
        }
    }
    return JsonObject(configValues)
}

private fun Any.toJsonPrimitive(): JsonPrimitive = when (this) {
    is String -> JsonPrimitive(this)
    is Boolean -> JsonPrimitive(this)
    is Number -> JsonPrimitive(this)
    else -> throw IllegalArgumentException("$this not supported!")
}
