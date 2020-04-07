package com.maximbircu.devtools.common.presentation.tools.time

import com.maximbircu.devtools.common.core.createTool
import com.maximbircu.devtools.common.mvp.BasePresenterTest
import com.maximbircu.devtools.common.utils.mockk
import com.maximbircu.devtools.common.utils.returns
import io.mockk.verify
import kotlin.test.Test

class TimeToolPresenterImplTest : BasePresenterTest<TimeToolView, TimeToolPresenter>(mockk()) {
    override fun createPresenter() = TimeToolPresenter.create(view)

    @Test
    fun `sets time on tool bind`() {
        val timeTool: TimeTool = createTool { store::restore returns 10000 }

        presenter.onToolBind(timeTool)

        verify { view.setTime("0d 0h 0m 10s 0ms") }
    }

    @Test
    fun `sets time when new time selected`() {
        presenter.onTimeSelected(Time("1d 2h 3m 4s 5ms"))

        verify { view.setTime("1d 2h 3m 4s 5ms") }
    }

    @Test
    fun `displays time selection dialog on click`() {
        val timeTool: TimeTool = createTool {
            ::title returns "Time Tool"
            store::restore returns 10000
        }
        presenter.onToolBind(timeTool)

        presenter.onClick("1d 2h 3m 4s 5ms")

        verify { view.displayTimeSelectionDialog(title = "Time Tool", time = "1d 2h 3m 4s 5ms") }
    }

    @Test
    fun `stores selected time on store config value`() {
        val timeTool: TimeTool = createTool { store::restore returns 10000 }
        presenter.onToolBind(timeTool)

        presenter.onStoreConfigValue("0d 0h 0m 10s 0ms")

        verify { timeTool.store.store(10000) }
    }
}
