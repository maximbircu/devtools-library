package com.maximbircu.devtools.common

import com.maximbircu.devtools.common.core.createTool
import com.maximbircu.devtools.common.extensions.forEachRecursively
import com.maximbircu.devtools.common.mvp.BaseTest
import com.maximbircu.devtools.common.presentation.tools.group.GroupTool
import com.maximbircu.devtools.common.readers.DevToolsSources
import com.maximbircu.devtools.common.readers.memory
import com.maximbircu.devtools.common.utils.mockk
import com.maximbircu.devtools.common.utils.returns
import io.mockk.verify
import kotlin.test.Test

class DevToolsImplTest : BaseTest() {
    @Test
    fun `persists tools state`() {
        val tools = mapOf(
            "first-tool" to createTool(),
            "second-tool" to createTool<GroupTool> {
                ::tools returns mapOf("child-tool" to createTool())
            }
        )
        val devTools = DevTools.create(DevToolsSources.memory(tools))

        devTools.persistToolsState()

        tools.forEachRecursively { _, tool -> verify { tool.persistState() } }
    }

    @Test
    fun `notifies listeners about dev tools configuration update`() {
        val onConfigUpdate: () -> Unit = mockk(relaxed = true)
        val devTools = DevTools.create(
            DevToolsSources.memory(emptyMap()),
            onConfigUpdate = onConfigUpdate
        )

        devTools.persistToolsState()

        verify { onConfigUpdate() }
    }
}
