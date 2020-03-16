package com.maximbircu.devtools.common.presentation.configscreen

import com.maximbircu.devtools.common.DevTools
import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.createTool
import com.maximbircu.devtools.common.mvp.BasePresenterTest
import com.maximbircu.devtools.common.presentation.list.DevToolsListView
import com.maximbircu.devtools.common.presentation.tool.DevToolView
import com.maximbircu.devtools.common.utils.mockk
import io.mockk.every
import io.mockk.verify
import kotlin.test.Test

class ConfigScreenPresenterImplTest :
    BasePresenterTest<ConfigScreenView, ConfigScreenPresenter>(mockk()) {
    private val devTools: DevTools = mockk()
    private val devToolsList: DevToolsListView = mockk()

    override fun createPresenter() = ConfigScreenPresenter.create(view, devTools, devToolsList)

    @Test
    fun `shows dev tools on create`() {
        val tools = mapOf<String, DevTool<Boolean>>(
            "first-tool" to createTool(),
            "second-tool" to createTool()
        )
        every { devTools.tools }.returns(tools)

        presenter.onCreate()

        verify { devToolsList.showDevTools(tools.values.toList()) }
    }

    @Test
    fun `triggers config update when the user applies config`() {
        val devToolsViews = listOf<DevToolView>(mockk(), mockk(), mockk())
        every { devTools.onConfigUpdate }.returns(mockk(relaxed = true))
        every { devToolsList.devToolViews }.returns(devToolsViews)

        presenter.onApplyConfig()

        devToolsList.devToolViews.forEach { view -> verify { view.triggerConfigUpdate() } }
    }

    @Test
    fun `notifies listener about configuration update on apply`() {
        val listener: () -> Unit = mockk(relaxed = true)
        every { devTools.onConfigUpdate }.returns(listener)
        every { devToolsList.devToolViews }.returns(emptyList())

        presenter.onApplyConfig()

        verify { listener.invoke() }
    }
}
