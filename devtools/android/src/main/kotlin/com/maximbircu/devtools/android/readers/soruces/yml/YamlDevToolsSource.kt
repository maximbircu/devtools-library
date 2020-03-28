package com.maximbircu.devtools.android.readers.soruces.yml

import android.content.res.AssetManager
import com.maximbircu.devtools.common.core.reader.DevToolsReader
import com.maximbircu.devtools.common.core.reader.DevToolsSource
import java.io.InputStream

internal class YamlDevToolsSource(
    private val assetManager: AssetManager,
    private val inputStream: InputStream
) : DevToolsSource {
    override fun getReader(): DevToolsReader = YamlDevToolsReader(
        YamlDevToolsTypesRegistry.create(assetManager.open("dev-tools-types.yml")),
        inputStream
    )
}
