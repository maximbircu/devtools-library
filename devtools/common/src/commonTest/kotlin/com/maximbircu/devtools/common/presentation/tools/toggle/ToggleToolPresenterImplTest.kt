package com.maximbircu.devtools.common.presentation.tools.toggle

import com.maximbircu.devtools.common.core.createTool
import com.maximbircu.devtools.common.mvp.BasePresenterTest
import com.maximbircu.devtools.common.utils.mockk
import com.maximbircu.devtools.common.utils.returns
import io.mockk.verify
import kotlin.test.Test

class ToggleToolPresenterImplTest :
    BasePresenterTest<ToggleToolView, ToggleToolPresenter>(mockk()) {
    override fun createPresenter() = ToggleToolPresenter.create(view)

    @Test
    fun `sets tool stored configuration value on tool bind`() {
        val tool: ToggleTool = createTool { ::value returns false }

        presenter.onToolBind(tool)

        verify { view.setValue(false) }
    }

    @Test
    fun `sets new configuration value on toggle value update`() {
        val tool: ToggleTool = createTool { ::value returns false }
        presenter.onToolBind(tool)

        presenter.onCheckedChangeListener(true)

        verify { tool.value = true }
    }
}
