package com.maximbircu.devtools.common.readers.jsonschema.factories

import com.maximbircu.devtools.common.presentation.tools.text.TextTool
import com.maximbircu.devtools.common.readers.jsonschema.JsonSchemaToolFactory
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.double
import kotlinx.serialization.json.jsonPrimitive

internal class JsonSchemaTextToolDoubleFactory(
    jsonObject: JsonObject
) : JsonSchemaToolFactory<TextTool>(jsonObject) {
    private val hint = jsonObject["hint"]?.jsonPrimitive

    override fun createDevTool() = TextTool(default?.double ?: 0.0, hint?.content)
}
