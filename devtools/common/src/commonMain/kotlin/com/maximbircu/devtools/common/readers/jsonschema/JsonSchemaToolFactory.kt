package com.maximbircu.devtools.common.readers.jsonschema

import com.maximbircu.devtools.common.core.DevTool
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.content

internal abstract class JsonSchemaToolFactory<T : DevTool<*>>(
    protected val jsonObject: JsonObject
) {
    protected val default: JsonPrimitive? = jsonObject["default"]?.primitive

    fun create(): T = createDevTool().apply {
        title = jsonObject["title"]?.content
        description = jsonObject["description"]?.content ?: ""
        canBeDisabled = jsonObject["canBeDisabled"]?.boolean ?: true
        defaultEnabledValue = jsonObject["defaultEnabledValue"]?.boolean ?: false
        isCritical = jsonObject["isCritical"]?.boolean ?: true
    }

    protected abstract fun createDevTool(): T
}
