package com.maximbircu.devtools.common.readers.jsonschema.factories

import com.maximbircu.devtools.common.presentation.tools.text.TextTool
import com.maximbircu.devtools.common.readers.jsonschema.JsonSchemaToolFactory
import kotlinx.serialization.json.JsonObject

internal class JsonSchemaTextToolFactory(
    jsonObject: JsonObject
) : JsonSchemaToolFactory<TextTool>(jsonObject) {
    private val hint = jsonObject["hint"]?.primitive

    override fun createDevTool() = TextTool(default?.content ?: "", hint?.content)
}
