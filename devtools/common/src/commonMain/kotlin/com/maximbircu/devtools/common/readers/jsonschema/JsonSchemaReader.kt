package com.maximbircu.devtools.common.readers.jsonschema

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.reader.DevToolsReader
import com.maximbircu.devtools.common.presentation.tools.group.GroupTool
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration.Companion.Stable
import kotlinx.serialization.json.JsonObject

internal class JsonSchemaReader(private val jsonSchemaString: String) : DevToolsReader {
    private val json = Json(Stable.copy(ignoreUnknownKeys = true))

    override fun getDevTools(): Map<String, DevTool<*>> {
        val jsonObject = json.parse(JsonObject.serializer(), jsonSchemaString)
        val factory = JsonSchemaDevToolFactoryProducer(jsonObject).getDevToolFactory()
        return (factory.create() as GroupTool).tools
    }
}
