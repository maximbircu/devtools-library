package com.maximbircu.devtools.common.readers.jsonschema

import com.maximbircu.devtools.common.mvp.BaseTest
import com.maximbircu.devtools.common.readers.jsonschema.factories.JsonSchemaEnumToolFactory
import com.maximbircu.devtools.common.readers.jsonschema.factories.JsonSchemaGroupToolFactory
import com.maximbircu.devtools.common.readers.jsonschema.factories.JsonSchemaTextToolDoubleFactory
import com.maximbircu.devtools.common.readers.jsonschema.factories.JsonSchemaTextToolFactory
import com.maximbircu.devtools.common.readers.jsonschema.factories.JsonSchemaTextToolIntegerFactory
import com.maximbircu.devtools.common.readers.jsonschema.factories.JsonSchemaToggleToolFactory
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonObject
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class JsonSchemaDevToolFactoryProducerTest : BaseTest() {
    @Test
    fun `creates toggle tool factory for boolean type`() {
        val jsonObject = buildJsonObject { put("type", "boolean") }
        val factoryProducer = JsonSchemaDevToolFactoryProducer(jsonObject)

        assertTrue(factoryProducer.getDevToolFactory() is JsonSchemaToggleToolFactory)
    }

    @Test
    fun `creates text tool factory for string type`() {
        val jsonObject = buildJsonObject { put("type", "string") }
        val factoryProducer = JsonSchemaDevToolFactoryProducer(jsonObject)

        assertTrue(factoryProducer.getDevToolFactory() is JsonSchemaTextToolFactory)
    }

    @Test
    fun `creates integer text tool factory for integer type`() {
        val jsonObject = buildJsonObject { put("type", "integer") }
        val factoryProducer = JsonSchemaDevToolFactoryProducer(jsonObject)

        assertTrue(factoryProducer.getDevToolFactory() is JsonSchemaTextToolIntegerFactory)
    }

    @Test
    fun `creates double text tool factory for number type`() {
        val jsonObject = buildJsonObject { put("type", "number") }
        val factoryProducer = JsonSchemaDevToolFactoryProducer(jsonObject)

        assertTrue(factoryProducer.getDevToolFactory() is JsonSchemaTextToolDoubleFactory)
    }

    @Test
    fun `creates group tool factory for object type`() {
        val jsonObject = buildJsonObject {
            put("type", "object")
            putJsonObject("properties") {}
        }
        val factoryProducer = JsonSchemaDevToolFactoryProducer(jsonObject)

        assertTrue(factoryProducer.getDevToolFactory() is JsonSchemaGroupToolFactory)
    }

    @Test
    fun `creates enum tool factory when enum tag is present`() {
        val jsonObject = buildJsonObject {
            put("type", "string")
            putJsonObject("enum") { }
        }
        val factoryProducer = JsonSchemaDevToolFactoryProducer(jsonObject)

        assertTrue(factoryProducer.getDevToolFactory() is JsonSchemaEnumToolFactory)
    }

    @Test
    fun `throws exception in case the object type is not supported`() {
        val jsonObject = buildJsonObject { put("type", "some not supported type") }
        val factoryProducer = JsonSchemaDevToolFactoryProducer(jsonObject)

        assertFailsWith(IllegalArgumentException::class) { factoryProducer.getDevToolFactory() }
    }
}
