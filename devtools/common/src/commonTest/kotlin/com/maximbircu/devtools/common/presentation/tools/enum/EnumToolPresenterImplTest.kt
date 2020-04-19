package com.maximbircu.devtools.common.presentation.tools.enum

import com.maximbircu.devtools.common.core.createTool
import com.maximbircu.devtools.common.mvp.BasePresenterTest
import com.maximbircu.devtools.common.utils.mockk
import com.maximbircu.devtools.common.utils.returns
import io.mockk.verify
import kotlin.test.Test

class EnumToolPresenterImplTest : BasePresenterTest<EnumToolView, EnumToolPresenter>(mockk()) {
    override fun createPresenter() = EnumToolPresenter.create(view)

    @Test
    fun `shows options without custom on tool bind`() {
        val fakeOptions = mapOf("first-option" to "first-value", "second-option" to "second-value")
        val tool: EnumTool = createTool {
            ::allowCustom returns false
            ::options returns fakeOptions
            ::value returns "first-value"
        }

        presenter.onToolBind(tool)

        verify { view.showOptions(fakeOptions.keys.toList()) }
    }

    @Test
    fun `shows options with custom on tool bind`() {
        val fakeOptions = mapOf("first-option" to "first-value", "second-option" to "second-value")
        val tool: EnumTool = createTool {
            ::allowCustom returns true
            ::options returns fakeOptions
            ::value returns "first-value"
        }

        presenter.onToolBind(tool)

        verify { view.showOptions(fakeOptions.keys.toList() + "custom") }
    }

    @Test
    fun `sets custom value on tool bind`() {
        val tool: EnumTool = createTool { ::value returns "second-value" }

        presenter.onToolBind(tool)

        verify { view.setCustomValue("second-value") }
    }

    @Test
    fun `checks selected value option on tool bind`() {
        val fakeOptions = mapOf("first-option" to "first-value", "second-option" to "second-value")
        val tool: EnumTool = createTool {
            ::options returns fakeOptions
            ::value returns "second-value"
        }

        presenter.onToolBind(tool)

        verify { view.selectOption("second-option") }
    }

    @Test
    fun `checks custom option if there is no any option for selected value`() {
        val fakeOptions = mapOf("first-option" to "first-value", "second-option" to "second-value")
        val tool: EnumTool = createTool {
            ::options returns fakeOptions
            ::value returns "some custom value"
        }

        presenter.onToolBind(tool)

        verify { view.selectOption("custom") }
    }

    @Test
    fun `shows custom value input if selected option is custom`() {
        presenter.onOptionSelected("custom")

        verify { view.showCustomValueInputView() }
    }

    @Test
    fun `hides custom value input view if selected option is not custom`() {
        presenter.onToolBind(createTool { ::value returns "some custom value" })

        presenter.onOptionSelected("second-option")

        verify { view.hideCustomValueInputView() }
    }

    @Test
    fun `sets proper option value when the selected option is not custom`() {
        val fakeOptions = mapOf("first-option" to "first-value", "second-option" to "second-value")
        val tool: EnumTool = createTool {
            ::allowCustom returns true
            ::options returns fakeOptions
            ::value returns "second-value"
        }
        presenter.onToolBind(tool)

        presenter.onOptionSelected(option = "second-option")

        verify { tool.value = "second-value" }
    }

    @Test
    fun `sets proper option value on custom value changes`() {
        val fakeOptions = mapOf("first-option" to "first-value", "second-option" to "second-value")
        val tool: EnumTool = createTool {
            ::allowCustom returns true
            ::options returns fakeOptions
            ::value returns "second-value"
        }
        presenter.onToolBind(tool)

        presenter.onCustomValueChanged("some custom value")

        verify { tool.value = "some custom value" }
    }
}
