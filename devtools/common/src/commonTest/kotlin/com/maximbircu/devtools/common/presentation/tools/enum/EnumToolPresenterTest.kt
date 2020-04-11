package com.maximbircu.devtools.common.presentation.tools.enum

import com.maximbircu.devtools.common.core.createTool
import com.maximbircu.devtools.common.mvp.BasePresenterTest
import com.maximbircu.devtools.common.utils.mockk
import com.maximbircu.devtools.common.utils.returns
import io.mockk.every
import io.mockk.verify
import kotlin.test.Test

class EnumToolPresenterTest : BasePresenterTest<EnumToolView, EnumToolPresenter>(mockk()) {
    override fun createPresenter() = EnumToolPresenter.create(view)

    @Test
    fun `shows options without custom on tool bind`() {
        val fakeOptions = mapOf("first-option" to "first-value", "second-option" to "second-value")
        val tool: EnumTool = createTool {
            ::allowCustom returns false
            ::options returns fakeOptions
            store::restore returns "first-value"
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
            store::restore returns "first-value"
        }

        presenter.onToolBind(tool)

        verify { view.showOptions(fakeOptions.keys.toList() + "custom") }
    }

    @Test
    fun `sets custom value on tool bind`() {
        val tool: EnumTool = createTool { store::restore returns "second-value" }

        presenter.onToolBind(tool)

        verify { view.setCustomValue("second-value") }
    }

    @Test
    fun `checks selected value option on tool bind`() {
        val fakeOptions = mapOf("first-option" to "first-value", "second-option" to "second-value")
        val tool: EnumTool = createTool {
            ::options returns fakeOptions
            store::restore returns "second-value"
            every { getOptionNameForValue(any()) } returns "second-option"
        }

        presenter.onToolBind(tool)

        verify { view.checkOption("second-option") }
    }

    @Test
    fun `checks custom option if there is no any option for selected value`() {
        val fakeOptions = mapOf("first-option" to "first-value", "second-option" to "second-value")
        val tool: EnumTool = createTool {
            ::options returns fakeOptions
            store::restore returns "some custom value"
            every { getOptionNameForValue(any()) } returns null
        }

        presenter.onToolBind(tool)

        verify { view.checkOption("custom") }
    }

    @Test
    fun `shows custom value input view if selected option is custom`() {
        presenter.onOptionSelected("custom")

        verify { view.showCustomValueInputView() }
    }

    @Test
    fun `hides custom value input view if selected option is not custom`() {
        presenter.onOptionSelected("second-option")

        verify { view.hideCustomValueInputView() }
    }

    @Test
    fun `stores proper option value when the selected option is not custom`() {
        val fakeOptions = mapOf("first-option" to "first-value", "second-option" to "second-value")
        val tool: EnumTool = createTool {
            ::allowCustom returns true
            ::options returns fakeOptions
            store::restore returns "second-value"
        }
        presenter.onToolBind(tool)

        presenter.onStoreConfigValue(selectedOption = "second-option")

        verify { tool.store.store("second-value") }
    }

    @Test
    fun `stores proper option value when the selected option is custom`() {
        val fakeOptions = mapOf("first-option" to "first-value", "second-option" to "second-value")
        val tool: EnumTool = createTool {
            ::allowCustom returns true
            ::options returns fakeOptions
            store::restore returns "second-value"
        }
        presenter.onToolBind(tool)
        every { view.customOptionValue } returns "some custom value"

        presenter.onStoreConfigValue(selectedOption = "custom")

        verify { tool.store.store("some custom value") }
    }
}
