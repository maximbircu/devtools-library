package com.maximbircu.devtools.common.presentation.list

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.createTool
import com.maximbircu.devtools.common.mvp.BasePresenterTest
import com.maximbircu.devtools.common.utils.mockk
import io.mockk.verify
import kotlin.test.Test

class DevToolsListPresenterImplTest :
    BasePresenterTest<DevToolsListView, DevToolsListPresenter>(mockk()) {
    override fun createPresenter(): DevToolsListPresenter = DevToolsListPresenter.create(view)

    @Test
    fun `shows dev tools on create`() {
        val tools = listOf<DevTool<Boolean>>(createTool(), createTool(), createTool())

        presenter.onBind(tools)

        verify { view.showDevTools(tools) }
    }

    @Test
    fun `update devtools on update`() {
        val tools = listOf<DevTool<Boolean>>(createTool(), createTool(), createTool())

        presenter.onBind(tools)

        verify { view.showDevTools(tools) }
    }
}
