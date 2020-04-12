package com.maximbircu.devtools.android.readers.soruces.yaml

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.reader.DevToolsReader
import com.maximbircu.devtools.common.presentation.tools.group.GroupTool
import org.yaml.snakeyaml.Yaml
import java.io.InputStream

internal class YamlDevToolsReader(
    typesRegistry: YamlDevToolsTypesRegistry,
    private val toolsInputStream: InputStream
) : DevToolsReader {
    private val yaml = Yaml(typesRegistry.constructor)

    override fun getDevTools(): Map<String, DevTool<Any>> {
        val devTools: Map<String, DevTool<Any>> = yaml.load(toolsInputStream) ?: emptyMap()
        devTools.setDevToolsKeys()
        return devTools
    }

    private fun Map<String, DevTool<*>>.setDevToolsKeys(keyPrefix: String = "") {
        forEach { (key, tool) ->
            tool.key = if (keyPrefix.isNotEmpty()) "$keyPrefix.$key" else key
            if (tool is GroupTool) {
                tool.tools.setDevToolsKeys(
                    if (keyPrefix.isNotEmpty()) "$keyPrefix.${tool.key}" else tool.key
                )
            }
        }
    }
}
