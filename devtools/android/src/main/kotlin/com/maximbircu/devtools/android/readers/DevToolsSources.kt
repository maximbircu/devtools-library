package com.maximbircu.devtools.android.readers

import android.content.res.AssetManager
import com.maximbircu.devtools.android.readers.soruces.MemoryDevToolsSource
import com.maximbircu.devtools.android.readers.soruces.yaml.YamlDevToolsSource
import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.reader.DevToolsSource
import com.maximbircu.devtools.common.readers.DevToolsSources
import java.io.InputStream

fun DevToolsSources.memory(devTools: Map<String, DevTool<*>>): DevToolsSource {
    return MemoryDevToolsSource(devTools)
}

fun DevToolsSources.yaml(assetManager: AssetManager, fileName: String): DevToolsSource {
    return yaml(assetManager, assetManager.open(fileName))
}

fun DevToolsSources.yaml(assetManager: AssetManager, inputStream: InputStream): DevToolsSource {
    return YamlDevToolsSource(assetManager, inputStream)
}
