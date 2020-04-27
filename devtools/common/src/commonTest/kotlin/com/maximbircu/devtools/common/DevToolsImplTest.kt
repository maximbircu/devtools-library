package com.maximbircu.devtools.common

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.createTool
import com.maximbircu.devtools.common.extensions.forEachRecursively
import com.maximbircu.devtools.common.mvp.BaseTest
import com.maximbircu.devtools.common.presentation.tools.group.GroupTool
import com.maximbircu.devtools.common.presentation.tools.text.TextTool
import com.maximbircu.devtools.common.presentation.tools.toggle.ToggleTool
import com.maximbircu.devtools.common.readers.DevToolsSources
import com.maximbircu.devtools.common.readers.memory
import com.maximbircu.devtools.common.utils.mockk
import com.maximbircu.devtools.common.utils.returns
import io.mockk.Called
import io.mockk.clearAllMocks
import io.mockk.spyk
import io.mockk.verify
import kotlin.test.Test

class DevToolsImplTest : BaseTest() {
    @Test
    fun `restores persisted state`() {
        val tools = mapOf(
            "first-tool" to createTool { ::hasUnsavedChanges returns true },
            "second-tool" to createTool<GroupTool> {
                ::tools returns mapOf(
                    "first-child-tool" to createTool<ToggleTool>(),
                    "second-child-tool" to createTool<TextTool> { ::hasUnsavedChanges returns true }
                )
            }
        )

        DevTools.create("TEST", DevToolsSources.memory(tools))

        tools.forEachRecursively { _, tool -> verify { tool.restorePersistedState() } }
    }

    @Test
    fun `persists tool state in case it has unsaved changes`() {
        val childTools = mapOf(
            "first-child-tool" to createTool<ToggleTool>(),
            "second-child-tool" to createTool<TextTool> { ::hasUnsavedChanges returns true }
        )
        val tools = mapOf(
            "first-tool" to createTool { ::hasUnsavedChanges returns true },
            "second-tool" to createTool<GroupTool> { ::tools returns childTools }
        )
        val devTools = DevTools.create("TEST", DevToolsSources.memory(tools))

        devTools.persistToolsState()

        verify { tools.getValue("first-tool").persistState() }
        verify { childTools.getValue("second-child-tool").persistState() }
    }

    @Test
    fun `notifies the listener about tools config update if at least one tool was updated`() {
        val onConfigUpdate: (Boolean) -> Unit = mockk(relaxed = true)
        val devTools = DevTools.create(
            "TEST",
            DevToolsSources.memory(
                mapOf(
                    "first-tool" to createTool { ::hasUnsavedChanges returns true },
                    "second-tool" to createTool()
                )
            ),
            onConfigUpdate = onConfigUpdate
        )

        devTools.persistToolsState()

        verify { onConfigUpdate(false) }
    }

    @Test
    fun `doesn't notify the listener about tools critical config update if there are no changes`() {
        val onConfigUpdate: (Boolean) -> Unit = mockk(relaxed = true)
        val devTools = DevTools.create(
            "TEST",
            DevToolsSources.memory(mapOf("first-tool" to createTool())),
            onConfigUpdate = onConfigUpdate
        )

        devTools.persistToolsState()

        verify(exactly = 0) { onConfigUpdate(any()) }
    }

    @Test
    fun `notifies the listener about tools critical config update if at least one happened`() {
        val onConfigUpdate: (Boolean) -> Unit = mockk(relaxed = true)
        val devTools = DevTools.create(
            "TEST",
            DevToolsSources.memory(
                mapOf(
                    "first-tool" to createTool {
                        ::hasUnsavedChanges returns true
                        ::isCritical returns true
                    },
                    "second-tool" to createTool()
                )
            ),
            onConfigUpdate = onConfigUpdate
        )

        devTools.persistToolsState()

        verify { onConfigUpdate(true) }
    }

    @Test
    fun `updates dev tools from provided params`() {
        val childTools = mapOf(
            "first-tool" to createTool { ::key returns "group-tool.first-tool" },
            "second-tool" to createTool<TextTool> {
                ::key returns "group-tool.second-tool"
                ::value returns "Config value"
            }
        )
        val tools = mapOf(
            "first-tool" to createTool<ToggleTool> {
                ::key returns "first-tool"
                ::value returns true
            },
            "group-tool" to createTool<GroupTool> {
                ::key returns "group-tool"
                ::tools returns childTools
            }
        )
        val devTools = spyk(DevTools.create("TEST", DevToolsSources.memory(tools)))

        devTools.updateFromParams(
            mapOf(
                "first-tool" to false,
                "group-tool.second-tool" to "New config value"
            )
        )

        verify { tools.getValue("first-tool").isEnabled = true }
        verify { childTools.getValue("second-tool").isEnabled = true }
        verify { tools.getValue("first-tool") setProperty "value" value false }
        verify { childTools.getValue("second-tool") setProperty "value" value "New config value" }
        verify { devTools.persistToolsState() }
    }

    @Test
    fun `doesn't touch the tools in case the provided params are empty`() {
        val childTools = mapOf<String, DevTool<*>>(
            "first-tool" to createTool { ::key returns "group-tool.first-tool" },
            "second-tool" to createTool<TextTool> {
                ::key returns "group-tool.second-tool"
                ::value returns "Config value"
            }
        )
        val tools = mapOf(
            "first-tool" to createTool<ToggleTool> {
                ::key returns "first-tool"
                ::value returns true
            },
            "group-tool" to createTool<GroupTool> {
                ::key returns "group-tool"
                ::tools returns childTools
            }
        )
        val devTools = spyk(DevTools.create("TEST", DevToolsSources.memory(tools)))
        clearAllMocks()

        devTools.updateFromParams(emptyMap())

        tools.values.forEach { tool -> verify { tool wasNot Called } }
        childTools.values.forEach { tool -> verify { tool wasNot Called } }
        verify(exactly = 0) { devTools.persistToolsState() }
    }
}
