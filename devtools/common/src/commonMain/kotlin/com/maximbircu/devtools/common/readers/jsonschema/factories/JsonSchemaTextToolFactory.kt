package com.maximbircu.devtools.common.readers.jsonschema.factories

import com.maximbircu.devtools.common.presentation.tools.text.TextTool
import com.maximbircu.devtools.common.readers.jsonschema.JsonSchemaToolFactory
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive

internal class JsonSchemaTextToolFactory(
    jsonObject: JsonObject
) : JsonSchemaToolFactory<TextTool>(jsonObject) {
    private val hint = jsonObject["hint"]?.jsonPrimitive

    override fun createDevTool() = TextTool(default?.content ?: "", hint?.content)
}
