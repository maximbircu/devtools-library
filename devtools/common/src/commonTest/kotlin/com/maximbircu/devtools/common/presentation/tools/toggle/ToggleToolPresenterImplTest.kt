package com.maximbircu.devtools.common.presentation.tools.toggle

import com.maximbircu.devtools.common.core.createTool
import com.maximbircu.devtools.common.mvp.PresenterTest
import com.maximbircu.devtools.common.utils.mockk
import io.mockk.verify
import kotlin.test.Test

class ToggleToolPresenterImplTest : PresenterTest<ToggleToolView, ToggleToolPresenter>(mockk()) {
    override fun createPresenter() = ToggleToolPresenter.create(view)

    @Test
    fun `sets tool stored configuration value on bind`() {
        val tool: ToggleTool = createTool { storedData = false }

        presenter.onBind(tool)

        verify { view.setValue(false) }
    }

    @Test
    fun `sets title on bind`() {
        val tool: ToggleTool = createTool {
            storedData = false
            title = "Toggle tool title"
        }

        presenter.onBind(tool)

        verify { view.setTitle("Toggle tool title") }
    }

    @Test
    fun `stores new configuration value on update`() {
        val tool: ToggleTool = createTool { storedData = false }
        presenter.onBind(tool)

        presenter.onUpdate(true)

        verify { tool.store.store(true) }
    }
}
