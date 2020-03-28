package com.maximbircu.devtools.android

import android.content.Context
import com.maximbircu.devtools.android.presentation.tool.DevToolLayout
import com.maximbircu.devtools.android.utils.mockk
import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.ToolStore
import com.maximbircu.devtools.common.presentation.tools.toggle.ToggleTool
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class DevToolsViewRegistryTest : BaseTest() {
    @Test
    fun `returns proper type index for model`() {
        val typeIndex = DevToolsViewRegistry.getTypeIndex(ToggleTool())

        assertEquals(0, typeIndex)
    }

    @Test
    fun `throws exception if the model was not registered`() {
        val fakeDevToolModel = object : DevTool<Boolean>() {
            override val store: ToolStore<Boolean> get() = mockk()
            override fun getDefaultValue() = false
        }

        assertFailsWith(NullPointerException::class) {
            DevToolsViewRegistry.getTypeIndex(fakeDevToolModel)
        }
    }

    @Test
    fun `returns proper view factory for model`() {
        val expectedViewFactory: (Context) -> DevToolLayout<*> = { mockk() }
        val fakeDevToolModel = object : DevTool<Boolean>() {
            override val store: ToolStore<Boolean> get() = mockk()
            override fun getDefaultValue() = false
        }
        DevToolsViewRegistry.register(fakeDevToolModel::class, expectedViewFactory)
        val fakModelTypeIndex = DevToolsViewRegistry.getTypeIndex(fakeDevToolModel)

        val actualViewFactory = DevToolsViewRegistry.getViewFactory(fakModelTypeIndex)

        assertEquals(expectedViewFactory, actualViewFactory)
    }

    @Test
    fun `throws exception when registering view if it's already registered`() {
        val fakeDevToolModel = object : DevTool<Boolean>() {
            override val store: ToolStore<Boolean> get() = mockk()
            override fun getDefaultValue() = false
        }
        DevToolsViewRegistry.register(fakeDevToolModel::class) { mockk() }


        assertFailsWith(IllegalArgumentException::class) {
            DevToolsViewRegistry.register(fakeDevToolModel::class) { mockk() }
        }
    }
}
