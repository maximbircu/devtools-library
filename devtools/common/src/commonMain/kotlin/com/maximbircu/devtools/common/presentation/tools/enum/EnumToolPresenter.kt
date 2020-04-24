package com.maximbircu.devtools.common.presentation.tools.enum

import com.maximbircu.devtools.common.core.mvp.BasePresenter
import com.maximbircu.devtools.common.core.mvp.Presenter

/**
 * Encapsulates the [EnumTool] business logic and displays enum tool data through [EnumToolView].
 */
interface EnumToolPresenter : Presenter {
    /**
     * Should be called as soon as a [EnumTool] instance has been provided to the [EnumToolView].
     *
     * @param tool the tool instance provided to the view
     */
    fun onToolBind(tool: EnumTool)

    /**
     * Should be called whenever the enum tool gets selected.
     */
    fun onToolClick()

    /**
     * Should be invoked whenever the option selector dialog closes by a configuration button click.
     */
    fun onPositiveButtonClick()

    /**
     * Should be invoked whenever the option selector dialog closes by a cancel button click.
     */
    fun onNegativeButtonClick()

    companion object {
        /**
         * Provides a new [EnumToolPresenter] instance.
         *
         * @param view a [EnumToolView] instance
         */
        fun create(view: EnumToolView): EnumToolPresenter = EnumToolPresenterImpl(view)
    }
}

/**
 * A compact enum tool option selector should be presented as soon as the options list size is
 * smaller than [MAX_ALLOWED_OPTIONS_FOR_COMPACT_MODE] and a dialog selector vice-versa.
 */
private const val MAX_ALLOWED_OPTIONS_FOR_COMPACT_MODE = 7

private class EnumToolPresenterImpl(
    view: EnumToolView
) : BasePresenter<EnumToolView>(view), EnumToolPresenter {
    private lateinit var tool: EnumTool
    private val isCompactMode get() = tool.options.size < MAX_ALLOWED_OPTIONS_FOR_COMPACT_MODE
    private lateinit var dialogSelectedValue: String

    override fun onToolBind(tool: EnumTool) {
        this.tool = tool
        this.dialogSelectedValue = tool.value
        if (isCompactMode) {
            view.showCompactOptionsSelector(tool) { tool.value = it }
        } else {
            view.showConfigurationValue(tool.value)
        }
    }

    override fun onToolClick() {
        if (!isCompactMode) {
            view.showOptionSelectorDialog(tool) { dialogSelectedValue = it }
        }
    }

    override fun onPositiveButtonClick() {
        tool.value = dialogSelectedValue
        view.showConfigurationValue(dialogSelectedValue)
    }

    override fun onNegativeButtonClick() {
        dialogSelectedValue = tool.value
    }
}
