package com.maximbircu.devtools.common.presentation.tools.enum

import com.maximbircu.devtools.common.core.createTool
import com.maximbircu.devtools.common.mvp.BasePresenterTest
import com.maximbircu.devtools.common.utils.mockk
import com.maximbircu.devtools.common.utils.returns
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.slot
import io.mockk.verify
import kotlin.test.Test

class EnumToolPresenterImplTest : BasePresenterTest<EnumToolView, EnumToolPresenter>(mockk()) {
    override fun createPresenter() = EnumToolPresenter.create(view)

    @Test
    fun `shows compact options selector if options size is smaller than 7`() {
        val tool: EnumTool = createCompactEnumTool()

        presenter.onToolBind(tool)

        verify { view.showCompactOptionsSelector(tool, any()) }
    }

    @Test
    fun `hides configuration value if options size is smaller than 7`() {
        val tool: EnumTool = createCompactEnumTool()

        presenter.onToolBind(tool)

        verify { view.hideConfigurationValue() }
    }

    @Test
    fun `shows configuration value if options size is bigger than 6`() {
        val tool: EnumTool = createExtendedEnumTool()

        presenter.onToolBind(tool)

        verify { view.showConfigurationValue("Second Option Value") }
    }

    @Test
    fun `hides compact option selector if options size is bigger than 6`() {
        val tool: EnumTool = createExtendedEnumTool()

        presenter.onToolBind(tool)

        verify { view.hideCompactOptionsSelector() }
    }

    @Test
    fun `shows option selector dialog on tool click if options size is bigger than 6`() {
        val tool: EnumTool = createExtendedEnumTool()
        presenter.onToolBind(tool)

        presenter.onToolClick()

        verify { view.showOptionSelectorDialog(tool, any()) }
    }

    @Test
    fun `doesn't show option selector dialog on tool click if options size is smaller than 7`() {
        val tool: EnumTool = createCompactEnumTool()
        presenter.onToolBind(tool)

        presenter.onToolClick()

        verify(exactly = 0) { view.showOptionSelectorDialog(any(), any()) }
    }

    @Test
    fun `sets tool value whenever a new option gets selected from compact selector`() {
        val tool: EnumTool = createCompactEnumTool()
        val onNewOptionSelectedSlot = slot<(String) -> Unit>()
        every { view.showCompactOptionsSelector(tool, capture(onNewOptionSelectedSlot)) } just Runs
        presenter.onToolBind(tool)

        onNewOptionSelectedSlot.captured.invoke("Third Option Value")

        verify { tool.value = "Third Option Value" }
    }

    @Test
    fun `sets tool value on option selector dialog positive button click`() {
        val tool: EnumTool = createExtendedEnumTool()
        val onNewOptionSelectedSlot = slot<(String) -> Unit>()
        every { view.showOptionSelectorDialog(tool, capture(onNewOptionSelectedSlot)) } just Runs
        presenter.onToolBind(tool)
        presenter.onToolClick()
        onNewOptionSelectedSlot.captured.invoke("Third Option Value")

        presenter.onPositiveButtonClick()

        verify { tool.value = "Third Option Value" }
    }

    @Test
    fun `resets tool selected value to default on option selector dialog negative button click`() {
        val tool: EnumTool = createExtendedEnumTool()
        val onNewOptionSelectedSlot = slot<(String) -> Unit>()
        every { view.showOptionSelectorDialog(tool, capture(onNewOptionSelectedSlot)) } just Runs
        presenter.onToolBind(tool)
        presenter.onToolClick()
        onNewOptionSelectedSlot.captured.invoke("Third Option Value")
        presenter.onNegativeButtonClick()

        presenter.onPositiveButtonClick()

        verify { tool.value = "Second Option Value" }
    }

    @Test
    fun `shows configuration value on positive button click`() {
        presenter.onToolBind(createCompactEnumTool())

        presenter.onPositiveButtonClick()

        verify { view.showConfigurationValue("Second Option Value") }
    }

    private fun createCompactEnumTool(): EnumTool = createTool {
        ::options returns mapOf(
            "first-option" to "First Option Value",
            "second-option" to "Second Option Value",
            "third-option" to "Third Option Value"
        )
        ::value returns "Second Option Value"
    }

    private fun createExtendedEnumTool(): EnumTool = createTool {
        ::options returns mapOf(
            "first-option" to "First Option Value",
            "second-option" to "Second Option Value",
            "third-option" to "Third Option Value",
            "fifth-option" to "Fifth Option Value",
            "sixth-option" to "Sixth Option Value",
            "seventh-option" to "Seventh Option Value",
            "eighth-option" to "Eighth Option Value",
            "ninth-option" to "Ninth Option Value"
        )
        ::value returns "Second Option Value"
    }
}
