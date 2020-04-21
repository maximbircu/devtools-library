package com.maximbircu.devtools.common.presentation.tools.text

import com.maximbircu.devtools.common.core.createTool
import com.maximbircu.devtools.common.mvp.BasePresenterTest
import com.maximbircu.devtools.common.utils.mockk
import com.maximbircu.devtools.common.utils.returns
import io.mockk.verify
import kotlin.test.Test
import kotlin.test.assertFailsWith

class TextToolPresenterImplTest : BasePresenterTest<TextToolView, TextToolPresenter>(mockk()) {
    override fun createPresenter(): TextToolPresenter = TextToolPresenter.create(view)

    @Test
    fun `sets text hint on tool bind`() {
        val tool: TextTool = createTool { ::hint returns "Tool hint" }

        presenter.onToolBind(tool)

        verify { view.setHint("Tool hint") }
    }

    @Test
    fun `sets input data type on tool bind`() {
        val tool: TextTool = createTool { ::configurationValueType returns String::class }

        presenter.onToolBind(tool)

        verify { view.setInputDataType(String::class) }
    }

    @Test
    fun `sets text value on tool bind`() {
        val tool: TextTool = createTool { ::value returns "text value" }

        presenter.onToolBind(tool)

        verify { view.setTextValue("text value") }
    }

    // region Stores the proper config value

    @Test
    fun `sets string value on config value change`() {
        val tool: TextTool = createTool { ::configurationValueType returns String::class }
        presenter.onToolBind(tool)

        presenter.onConfigValueChanged("text value")

        verify { tool.value = "text value" }
    }

    @Test
    fun `sets int value on config value change`() {
        val tool: TextTool = createTool { ::configurationValueType returns Int::class }
        presenter.onToolBind(tool)

        presenter.onConfigValueChanged("3")

        verify { tool.value = 3 }
    }

    @Test
    fun `sets long value on config value change`() {
        val tool: TextTool = createTool { ::configurationValueType returns Long::class }
        presenter.onToolBind(tool)

        presenter.onConfigValueChanged("3")

        verify { tool.value = 3L }
    }

    @Test
    fun `sets float value on config value change`() {
        val tool: TextTool = createTool { ::configurationValueType returns Float::class }
        presenter.onToolBind(tool)

        presenter.onConfigValueChanged("3")

        verify { tool.value = 3f }
    }

    @Test
    fun `sets double value on config value change`() {
        val tool: TextTool = createTool { ::configurationValueType returns Double::class }
        presenter.onToolBind(tool)

        presenter.onConfigValueChanged("3.0")

        verify { tool.value = 3.0 }
    }

    @Test
    fun `throws exception if tool type is not supported`() {
        val value = object {}
        val tool: TextTool = createTool { ::configurationValueType returns value::class }
        presenter.onToolBind(tool)

        assertFailsWith(IllegalArgumentException::class) {
            presenter.onConfigValueChanged(value.toString())
        }
    }

    // endregion
}
