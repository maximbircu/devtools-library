package com.maximbircu.devtools.common.readers.jsonschema.factories

import com.maximbircu.devtools.common.presentation.tools.enum.EnumOptionsProvider
import com.maximbircu.devtools.common.presentation.tools.enum.EnumTool
import com.maximbircu.devtools.common.readers.jsonschema.JsonSchemaToolFactory
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonPrimitive

internal class JsonSchemaEnumToolFactory(
    jsonObject: JsonObject
) : JsonSchemaToolFactory<EnumTool>(jsonObject) {
    override fun createDevTool(): EnumTool = EnumTool(
        defaultValueKey = default?.content,
        allowCustom = jsonObject["allowCustom"]?.jsonPrimitive?.boolean ?: false,
        optionsProvider = object : EnumOptionsProvider {
            override fun getOptions(): Map<String, String> {
                val enumOptions = jsonObject.getValue("enum").jsonArray
                return enumOptions.map { it.jsonPrimitive.content to it.jsonPrimitive.content }
                    .toMap()
            }
        }
    )
}
