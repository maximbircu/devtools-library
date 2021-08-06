package com.maximbircu.devtools.common.readers.jsonschema

import com.maximbircu.devtools.common.readers.jsonschema.factories.JsonSchemaEnumToolFactory
import com.maximbircu.devtools.common.readers.jsonschema.factories.JsonSchemaGroupToolFactory
import com.maximbircu.devtools.common.readers.jsonschema.factories.JsonSchemaTextToolDoubleFactory
import com.maximbircu.devtools.common.readers.jsonschema.factories.JsonSchemaTextToolFactory
import com.maximbircu.devtools.common.readers.jsonschema.factories.JsonSchemaTextToolIntegerFactory
import com.maximbircu.devtools.common.readers.jsonschema.factories.JsonSchemaToggleToolFactory
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

internal class JsonSchemaDevToolFactoryProducer(private val jsonObject: JsonObject) {
    private val type = jsonObject["type"]!!.jsonPrimitive.content
    private val properties: Map<String, JsonSchemaToolFactory<*>>
        get() = jsonObject["properties"]!!.jsonObject.toFactoriesMap()

    init {
        jsonObject["type"]?.jsonPrimitive?.content
    }

    fun getDevToolFactory(): JsonSchemaToolFactory<*> {
        return if (jsonObject["enum"] != null) {
            JsonSchemaEnumToolFactory(jsonObject)
        } else {
            when (type) {
                "boolean" -> JsonSchemaToggleToolFactory(jsonObject)
                "string" -> JsonSchemaTextToolFactory(jsonObject)
                "integer" -> JsonSchemaTextToolIntegerFactory(jsonObject)
                "number" -> JsonSchemaTextToolDoubleFactory(jsonObject)
                "object" -> JsonSchemaGroupToolFactory(jsonObject, properties)
                else -> throw IllegalArgumentException("$type not supported!")
            }
        }
    }

    private fun JsonObject.toFactoriesMap() = map { (key, value) ->
        key to JsonSchemaDevToolFactoryProducer(value.jsonObject).getDevToolFactory()
    }.toMap()
}
