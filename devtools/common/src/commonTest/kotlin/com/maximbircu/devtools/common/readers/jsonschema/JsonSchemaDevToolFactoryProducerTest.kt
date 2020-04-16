package com.maximbircu.devtools.common.readers.jsonschema

import com.maximbircu.devtools.common.mvp.BaseTest
import com.maximbircu.devtools.common.readers.jsonschema.factories.JsonSchemaEnumToolFactory
import com.maximbircu.devtools.common.readers.jsonschema.factories.JsonSchemaGroupToolFactory
import com.maximbircu.devtools.common.readers.jsonschema.factories.JsonSchemaTextToolDoubleFactory
import com.maximbircu.devtools.common.readers.jsonschema.factories.JsonSchemaTextToolFactory
import com.maximbircu.devtools.common.readers.jsonschema.factories.JsonSchemaTextToolIntegerFactory
import com.maximbircu.devtools.common.readers.jsonschema.factories.JsonSchemaToggleToolFactory
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class JsonSchemaDevToolFactoryProducerTest : BaseTest() {
    @Test
    fun `creates toggle tool factory for boolean type`() {
        val jsonObject = json { "type" to "boolean" }
        val factoryProducer = JsonSchemaDevToolFactoryProducer(jsonObject)

        assertTrue(factoryProducer.getDevToolFactory() is JsonSchemaToggleToolFactory)
    }

    @Test
    fun `creates text tool factory for string type`() {
        val jsonObject = json { "type" to "string" }
        val factoryProducer = JsonSchemaDevToolFactoryProducer(jsonObject)

        assertTrue(factoryProducer.getDevToolFactory() is JsonSchemaTextToolFactory)
    }

    @Test
    fun `creates integer text tool factory for integer type`() {
        val jsonObject = json { "type" to "integer" }
        val factoryProducer = JsonSchemaDevToolFactoryProducer(jsonObject)

        assertTrue(factoryProducer.getDevToolFactory() is JsonSchemaTextToolIntegerFactory)
    }

    @Test
    fun `creates double text tool factory for number type`() {
        val jsonObject = json { "type" to "number" }
        val factoryProducer = JsonSchemaDevToolFactoryProducer(jsonObject)

        assertTrue(factoryProducer.getDevToolFactory() is JsonSchemaTextToolDoubleFactory)
    }

    @Test
    fun `creates group tool factory for object type`() {
        val jsonObject = json {
            "type" to "object"
            "properties" to json { }
        }
        val factoryProducer = JsonSchemaDevToolFactoryProducer(jsonObject)

        assertTrue(factoryProducer.getDevToolFactory() is JsonSchemaGroupToolFactory)
    }

    @Test
    fun `creates enum tool factory when enum tag is present`() {
        val jsonObject = json {
            "type" to "string"
            "enum" to jsonArray { }
        }
        val factoryProducer = JsonSchemaDevToolFactoryProducer(jsonObject)

        assertTrue(factoryProducer.getDevToolFactory() is JsonSchemaEnumToolFactory)
    }

    @Test
    fun `throws exception in case the object type is not supported`() {
        val jsonObject = json { "type" to "some not supported type" }
        val factoryProducer = JsonSchemaDevToolFactoryProducer(jsonObject)

        assertFailsWith(IllegalArgumentException::class) { factoryProducer.getDevToolFactory() }
    }
}
