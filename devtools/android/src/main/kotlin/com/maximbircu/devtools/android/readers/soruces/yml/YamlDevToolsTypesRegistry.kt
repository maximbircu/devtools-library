package com.maximbircu.devtools.android.readers.soruces.yml

import org.yaml.snakeyaml.TypeDescription
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor
import java.io.InputStream

internal interface YamlDevToolsTypesRegistry {
    val constructor: Constructor

    companion object {
        fun create(vararg inputStream: InputStream): YamlDevToolsTypesRegistry {
            return YamlDevToolsTypesRegistryImpl(inputStream)
        }
    }
}

private class YamlDevToolsTypesRegistryImpl(
    inputStream: Array<out InputStream>
) : YamlDevToolsTypesRegistry {
    private val yaml = Yaml()
    override val constructor = Constructor()

    init {
        inputStream.forEach { stream -> stream.loadTypeDescriptions() }
    }

    private fun InputStream.loadTypeDescriptions() {
        val types = yaml.load<Map<String, String>>(this)
        types.forEach { (classPackageName, toolName) ->
            val type = Class.forName(classPackageName)
            constructor.addTypeDescription(TypeDescription(type, toolName))
        }
    }
}
