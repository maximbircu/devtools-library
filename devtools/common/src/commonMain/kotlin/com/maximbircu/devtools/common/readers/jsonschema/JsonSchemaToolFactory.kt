package com.maximbircu.devtools.common.readers.jsonschema

import com.maximbircu.devtools.common.core.DevTool
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.jsonPrimitive

internal abstract class JsonSchemaToolFactory<T : DevTool<*>>(
    protected val jsonObject: JsonObject
) {
    protected val default: JsonPrimitive? = jsonObject["default"]?.jsonPrimitive

    fun create(): T = createDevTool().apply {
        title = jsonObject["title"]?.jsonPrimitive?.content
        description = jsonObject["description"]?.jsonPrimitive?.content ?: ""
        canBeDisabled = jsonObject["canBeDisabled"]?.jsonPrimitive?.boolean ?: true
        defaultEnabledValue = jsonObject["defaultEnabledValue"]?.jsonPrimitive?.boolean ?: false
        isCritical = jsonObject["isCritical"]?.jsonPrimitive?.boolean ?: true
    }

    protected abstract fun createDevTool(): T
}
