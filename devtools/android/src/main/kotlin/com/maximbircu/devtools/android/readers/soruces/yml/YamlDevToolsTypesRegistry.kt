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
        inputStream.forEach { stream ->
            stream.load<Map<String, String>>()?.asIterable()?.associateTo(
                mutableMapOf(),
                { entry -> Class.forName(entry.key) to entry.value }
            )?.let(::addTypeDescriptions)
        }
    }

    private fun addTypeDescriptions(typeDescriptions: Map<Class<*>, String>) {
        typeDescriptions.forEach { (key, value) ->
            constructor.addTypeDescription(TypeDescription(key, value))
        }
    }

    private fun <T> InputStream.load(): T? {
        val data = reader().readText()
        if (data.isBlank()) {
            return null
        }
        return yaml.load(data) as T
    }
}
