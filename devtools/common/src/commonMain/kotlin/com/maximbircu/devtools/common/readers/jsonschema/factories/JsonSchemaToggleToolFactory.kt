package com.maximbircu.devtools.common.readers.jsonschema.factories

import com.maximbircu.devtools.common.presentation.tools.toggle.ToggleTool
import com.maximbircu.devtools.common.readers.jsonschema.JsonSchemaToolFactory
import kotlinx.serialization.json.JsonObject

internal class JsonSchemaToggleToolFactory(
    jsonObject: JsonObject
) : JsonSchemaToolFactory<ToggleTool>(jsonObject) {
    override fun createDevTool(): ToggleTool {
        return if (default == null) ToggleTool() else ToggleTool(default.boolean)
    }
}
