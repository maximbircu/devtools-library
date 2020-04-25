package com.maximbircu.devtools.common.presentation.tools.group

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.createTool
import com.maximbircu.devtools.common.mvp.BasePresenterTest
import com.maximbircu.devtools.common.utils.mockk
import com.maximbircu.devtools.common.utils.returns
import io.mockk.verify
import kotlin.test.Test

class GroupToolPresenterImplTest : BasePresenterTest<GroupToolView, GroupToolPresenter>(mockk()) {
    override fun createPresenter() = GroupToolPresenter.create(view)

    @Test
    fun `shows tools on tool bind`() {
        val fakeTools = mapOf<String, DevTool<*>>(
            "first-tool" to createTool(),
            "second-tool" to createTool(),
            "third-tool" to createTool()
        )
        val tool: GroupTool = createTool { ::tools returns fakeTools }

        presenter.onToolBind(tool)

        verify { view.showTools(fakeTools.values.toList()) }
    }

    // region Context Menu

    @Test
    fun `enables tools that can be enabled on enable`() {
        val fakeTools = mapOf<String, DevTool<*>>(
            "first-tool" to createTool { ::canBeDisabled returns true },
            "second-tool" to createTool { ::canBeDisabled returns false },
            "third-tool" to createTool { ::canBeDisabled returns true }
        )
        val tool: GroupTool = createTool { ::tools returns fakeTools }
        presenter.onToolBind(tool)

        presenter.onEnableAll()

        verify { fakeTools.getValue("first-tool").isEnabled = true }
        verify(exactly = 0) { fakeTools.getValue("second-tool").isEnabled = true }
        verify { fakeTools.getValue("third-tool").isEnabled = true }
    }

    @Test
    fun `disables tools that can be enabled on enable`() {
        val fakeTools = mapOf<String, DevTool<*>>(
            "first-tool" to createTool { ::canBeDisabled returns true },
            "second-tool" to createTool { ::canBeDisabled returns false },
            "third-tool" to createTool { ::canBeDisabled returns true }
        )
        val tool: GroupTool = createTool { ::tools returns fakeTools }
        presenter.onToolBind(tool)

        presenter.onDisableAll()

        verify { fakeTools.getValue("first-tool").isEnabled = false }
        verify(exactly = 0) { fakeTools.getValue("second-tool").isEnabled = false }
        verify { fakeTools.getValue("third-tool").isEnabled = false }
    }

    @Test
    fun `refreshes tool data presentation on enable all`() {
        val tool: GroupTool = createTool { ::tools returns emptyMap() }
        presenter.onToolBind(tool)

        presenter.onEnableAll()

        verify { view.refreshToolData() }
    }

    @Test
    fun `refreshes tool data presentation on disable all`() {
        val tool: GroupTool = createTool { ::tools returns emptyMap() }
        presenter.onToolBind(tool)

        presenter.onDisableAll()

        verify { view.refreshToolData() }
    }

    @Test
    fun `resets all tools to default`() {
        val fakeTools = mapOf<String, DevTool<*>>(
            "first-tool" to createTool(),
            "second-tool" to createTool(),
            "third-tool" to createTool()
        )
        val tool: GroupTool = createTool { ::tools returns fakeTools }
        presenter.onToolBind(tool)

        presenter.onSetAllToDefault()

        fakeTools.values.forEach { verify { it.resetToDefault() } }
    }

    @Test
    fun `resets all changes`() {
        val fakeTools = mapOf<String, DevTool<*>>(
            "first-tool" to createTool(),
            "second-tool" to createTool(),
            "third-tool" to createTool()
        )
        val tool: GroupTool = createTool { ::tools returns fakeTools }
        presenter.onToolBind(tool)

        presenter.onResetAllChanges()

        fakeTools.values.forEach { verify { it.restorePersistedState() } }
    }

    @Test
    fun `refreshes tool data presentation on set all to defaults`() {
        val tool: GroupTool = createTool { ::tools returns emptyMap() }
        presenter.onToolBind(tool)

        presenter.onSetAllToDefault()

        verify { view.refreshToolData() }
    }

    @Test
    fun `refreshes tool data presentation on reset all changes`() {
        val tool: GroupTool = createTool { ::tools returns emptyMap() }
        presenter.onToolBind(tool)

        presenter.onResetAllChanges()

        verify { view.refreshToolData() }
    }

    // endregion
}
