package com.maximbircu.devtools.common.presentation.configscreen

import com.maximbircu.devtools.common.DevTools
import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.createTool
import com.maximbircu.devtools.common.mvp.BasePresenterTest
import com.maximbircu.devtools.common.presentation.list.DevToolsListView
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
        every { devTools.tools } returns tools

        presenter.onCreate()

        verify { devToolsList.showDevTools(tools.values.toList()) }
    }

    @Test
    fun `persists tool state on apply config`() {
        presenter.onApplyConfig()

        devTools.persistToolsState()
    }
}
