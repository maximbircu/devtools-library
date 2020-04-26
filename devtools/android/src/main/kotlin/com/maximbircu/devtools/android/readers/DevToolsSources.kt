package com.maximbircu.devtools.android.readers

import android.content.res.AssetManager
import com.maximbircu.devtools.android.readers.soruces.yaml.YamlDevToolsSource
import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.reader.DevToolsSource
import com.maximbircu.devtools.common.readers.DevToolsSources
import com.maximbircu.devtools.common.readers.json
import java.io.InputStream

/**
 * Provides a [YamlDevToolsSource] which is a [DevToolsSource] implementation able to parse and
 * generate a dictionary of [DevTool] from YML.
 *
 * Use the provided source object as a parameter to [com.maximbircu.devtools.common.DevTools.create]
 * to enable [com.maximbircu.devtools.common.DevTools] read and provide dev tools from a YML config.
 *
 * @param assetManager used to load the dev tools types from a file stored inside the library assets
 * @param inputStream a stream which contains the dev tools configuration content in YML format
 */
fun DevToolsSources.yaml(assetManager: AssetManager, inputStream: InputStream): DevToolsSource {
    return YamlDevToolsSource(assetManager, inputStream)
}

/**
 * This method is doing the same as [DevToolsSources.yaml] allowing you to provide a file name
 * instead of an input stream as a parameter.
 *
 * Use this in case you store your dev tools config as a file inside your app's assets.
 *
 * @throws java.io.IOException
 *
 * @param assetManager used to load the YML file from your app assets
 * @param fileName dev tools configuration YML filename ex: "my-app-config.yml"
 */
fun DevToolsSources.yaml(assetManager: AssetManager, fileName: String): DevToolsSource {
    return yaml(assetManager, assetManager.open(fileName))
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
fun DevToolsSources.json(assetManager: AssetManager, fileName: String): DevToolsSource {
    return json(assetManager.open(fileName).reader().readText())
}
