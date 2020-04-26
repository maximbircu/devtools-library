package com.maximbircu.devtools.common.presentation.tool

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.createTool
import com.maximbircu.devtools.common.mvp.BasePresenterTest
import com.maximbircu.devtools.common.presentation.tool.help.DevToolHelpDialogPresenter
import com.maximbircu.devtools.common.presentation.tool.help.DevToolHelpDialogView
import com.maximbircu.devtools.common.utils.mockk
import com.maximbircu.devtools.common.utils.returns
import io.mockk.verify
import kotlin.test.Test

class DevToolHelpDialogPresenterImplTest :
    BasePresenterTest<DevToolHelpDialogView, DevToolHelpDialogPresenter>(mockk()) {
    override fun createPresenter() = DevToolHelpDialogPresenter.create(view)

    @Test
    fun `hides description if its not present on tool bind`() {
        val tool: DevTool<*> = createTool()

        presenter.onToolBind(tool)

        verify { view.hideDescription() }
    }

    @Test
    fun `sets description if its present on tool bind`() {
        val tool: DevTool<*> = createTool {
            ::description returns "Dev tool description"
        }

        presenter.onToolBind(tool)

        verify { view.setDescription("Dev tool description") }
    }

    @Test
    fun `sets key on tool bind`() {
        val tool: DevTool<*> = createTool {
            ::key returns "toggle-tool"
        }

        presenter.onToolBind(tool)

        verify { view.setKey("toggle-tool") }
    }

    @Test
    fun `sets current configuration value on tool bind`() {
        val tool: DevTool<*> = createTool {
            ::value returns "Current configuration value"
        }

        presenter.onToolBind(tool)

        verify { view.setCurrentConfigValue("Current configuration value") }
    }

    @Test
    fun `default configuration value on tool bind`() {
        val tool: DevTool<*> = createTool {
            ::getDefaultValue returns "Default configuration value"
        }

        presenter.onToolBind(tool)

        verify { view.setDefaultConfigValue("Default configuration value") }
    }

    @Test
    fun `shows critical update label on tool bind if tool is critical`() {
        val tool: DevTool<*> = createTool { ::isCritical returns true }

        presenter.onToolBind(tool)

        verify { view.showCriticalUpdateLabel() }
    }

    @Test
    fun `hides critical update label on tool bind if tool is not critical`() {
        val tool: DevTool<*> = createTool { ::isCritical returns false }

        presenter.onToolBind(tool)

        verify { view.hideCriticalUpdateLabel() }
    }
}
