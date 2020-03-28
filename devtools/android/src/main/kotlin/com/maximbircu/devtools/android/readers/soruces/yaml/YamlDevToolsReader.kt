package com.maximbircu.devtools.android.readers.soruces.yaml

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.reader.DevToolsReader
import org.yaml.snakeyaml.Yaml
import java.io.InputStream

internal class YamlDevToolsReader(
    typesRegistry: YamlDevToolsTypesRegistry,
    private val toolsInputStream: InputStream
) : DevToolsReader {
    private val yaml = Yaml(typesRegistry.constructor)

    override fun getDevTools(): Map<String, DevTool<Any>> {
        val devTools: Map<String, DevTool<Any>> = yaml.load(toolsInputStream) ?: emptyMap()
        devTools.forEach { (key, tool) -> tool.key = key }
        return devTools
    }
}
