package com.maximbircu.devtools.common.presentation.tools.group

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.createTool
import com.maximbircu.devtools.common.mvp.BasePresenterTest
import com.maximbircu.devtools.common.presentation.tool.DevToolView
import com.maximbircu.devtools.common.utils.mockk
import com.maximbircu.devtools.common.utils.returns
import io.mockk.every
import io.mockk.verify
import kotlin.test.Test

class GroupToolPresenterTest : BasePresenterTest<GroupToolView, GroupToolPresenter>(mockk()) {
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

    @Test
    fun `persists child tool state on store configuration value`() {
        val fakeToolsViews = listOf<DevToolView>(mockk(), mockk(), mockk())
        every { view.devToolsViews } returns fakeToolsViews

        presenter.onStoreConfigurationValue()

        fakeToolsViews.forEach { tool -> verify { tool.persistToolState() } }
    }
}
