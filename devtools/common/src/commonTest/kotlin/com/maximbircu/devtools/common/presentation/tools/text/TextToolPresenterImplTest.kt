package com.maximbircu.devtools.common.presentation.tools.text

import com.maximbircu.devtools.common.core.createTool
import com.maximbircu.devtools.common.mvp.BasePresenterTest
import com.maximbircu.devtools.common.utils.mockk
import com.maximbircu.devtools.common.utils.returns
import io.mockk.verify
import kotlin.test.Test
import kotlin.test.assertFails

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
        val tool: TextTool = createTool { store::restore returns "text value" }

        presenter.onToolBind(tool)

        verify { view.setTextValue("text value") }
    }

    // region Stores the proper config value

    @Test
    fun `stores string value on store config value`() {
        val tool: TextTool = createTool { ::configurationValueType returns String::class }
        presenter.onToolBind(tool)

        presenter.onStoreConfigValue("text value")

        verify { tool.store.store("text value") }
    }

    @Test
    fun `stores int value on store config value`() {
        val tool: TextTool = createTool { ::configurationValueType returns Int::class }
        presenter.onToolBind(tool)

        presenter.onStoreConfigValue("3")

        verify { tool.store.store(3) }
    }

    @Test
    fun `stores long value on store config value`() {
        val tool: TextTool = createTool { ::configurationValueType returns Long::class }
        presenter.onToolBind(tool)

        presenter.onStoreConfigValue("3")

        verify { tool.store.store(3L) }
    }

    @Test
    fun `stores float value on store config value`() {
        val tool: TextTool = createTool { ::configurationValueType returns Float::class }
        presenter.onToolBind(tool)

        presenter.onStoreConfigValue("3")

        verify { tool.store.store(3f) }
    }

    @Test
    fun `stores double value on store config value`() {
        val tool: TextTool = createTool { ::configurationValueType returns Double::class }
        presenter.onToolBind(tool)

        presenter.onStoreConfigValue("3.0")

        verify { tool.store.store(3.0) }
    }

    @Test
    fun `throws exception if tool type is not supported`() {
        val value = object {}
        val tool: TextTool = createTool { ::configurationValueType returns value::class }
        presenter.onToolBind(tool)

        assertFails { presenter.onStoreConfigValue(value.toString()) }
    }

    // endregion
}
