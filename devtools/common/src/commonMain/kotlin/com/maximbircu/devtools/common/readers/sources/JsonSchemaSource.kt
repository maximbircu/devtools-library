package com.maximbircu.devtools.common.readers.sources

import com.maximbircu.devtools.common.core.reader.DevToolsReader
import com.maximbircu.devtools.common.core.reader.DevToolsSource
import com.maximbircu.devtools.common.readers.jsonschema.JsonSchemaReader

internal class JsonSchemaSource(private val jsonString: String) : DevToolsSource {
    override fun getReader(): DevToolsReader = JsonSchemaReader(jsonString)
}
