package com.maximbircu.devtools.common.readers.jsonschema.factories

import com.maximbircu.devtools.common.presentation.tools.group.GroupTool
import com.maximbircu.devtools.common.readers.jsonschema.JsonSchemaToolFactory
import kotlinx.serialization.json.JsonObject

internal class JsonSchemaGroupToolFactory(
    jsonObject: JsonObject,
    private val properties: Map<String, JsonSchemaToolFactory<*>>
) : JsonSchemaToolFactory<GroupTool>(jsonObject) {
    override fun createDevTool(): GroupTool {
        return GroupTool(properties.map { (key, value) -> key to value.create() }.toMap())
    }
}
