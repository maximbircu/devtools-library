package com.maximbircu.devtools.common.readers

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.reader.DevToolsSource
import com.maximbircu.devtools.common.readers.sources.JsonSchemaSource
import com.maximbircu.devtools.common.readers.sources.MemoryDevToolsSource

/**
 * Provides the supported dev tools sources.
 */
object DevToolsSources

/**
 * Provides a [MemoryDevToolsSource] which is a [DevToolsSource] that allows you to pass a
 * dev tools dictionary directly to the [com.maximbircu.devtools.common.DevTools].
 *
 * Use this when you build the dev tools map inside your app and want to provide it to a
 * [com.maximbircu.devtools.common.DevTools].
 *
 * @param devTools dev tools dictionary to be passed to [com.maximbircu.devtools.common.DevTools]
 */
fun DevToolsSources.memory(devTools: Map<String, DevTool<*>>): DevToolsSource {
    return MemoryDevToolsSource(devTools)
}

/**
 * Provides a [JsonSchemaSource] which is a [DevToolsSource] implementation able to parse and
 * generate a dictionary of [DevTool] from JSON schema.
 *
 * Use the provided source object as a parameter to [com.maximbircu.devtools.common.DevTools.create]
 * to enable [com.maximbircu.devtools.common.DevTools] read and provide dev tools from a
 * JSON schema config.
 *
 * @param jsonString a string which contains the dev tools configuration content in JSON format
 */
fun DevToolsSources.json(jsonString: String): DevToolsSource = JsonSchemaSource(jsonString)
