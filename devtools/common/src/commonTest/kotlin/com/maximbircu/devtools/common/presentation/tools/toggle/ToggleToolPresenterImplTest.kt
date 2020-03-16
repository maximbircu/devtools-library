package com.maximbircu.devtools.common.presentation.tools.toggle

import com.maximbircu.devtools.common.core.createTool
import com.maximbircu.devtools.common.mvp.BasePresenterTest
import com.maximbircu.devtools.common.utils.mockk
import io.mockk.verify
import kotlin.test.Test

class ToggleToolPresenterImplTest :
    BasePresenterTest<ToggleToolView, ToggleToolPresenter>(mockk()) {
    override fun createPresenter() = ToggleToolPresenter.create(view)

    @Test
    fun `sets tool stored configuration value on bind`() {
        val tool: ToggleTool = createTool { storedData = false }

        presenter.onToolBind(tool)

        verify { view.setValue(false) }
    }

    @Test
    fun `stores new configuration value on update`() {
        val tool: ToggleTool = createTool { storedData = false }
        presenter.onToolBind(tool)

        presenter.onStoreConfigValue(true)

        verify { tool.store.store(true) }
    }
}
