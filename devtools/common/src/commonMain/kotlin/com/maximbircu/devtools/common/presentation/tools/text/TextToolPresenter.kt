package com.maximbircu.devtools.common.presentation.tools.text

import com.maximbircu.devtools.common.core.mvp.BasePresenter
import com.maximbircu.devtools.common.core.mvp.Presenter

/**
 * Encapsulates the [TextTool] business logic and displays text tool data through [TextToolView].
 */
interface TextToolPresenter : Presenter {

    /**
     * Should be called as soon as a [TextTool] instance was provided to [TextToolView].
     *
     * @param tool the tool instance provided to the view
     */
    fun onToolBind(tool: TextTool)

    /**
     * Should be called as soon as a configuration update was triggered which happens whenever
     * [com.maximbircu.devtools.common.presentation.tool.DevToolView.persistToolState] gets invoked.
     *
     * Will persist the [value] when called.
     *
     * @param value the configuration value presented at the invocation moment
     */
    fun onStoreConfigValue(value: String)

    companion object {
        /**
         * Provides a new [TextToolPresenter] instance.
         *
         * @param view a [TextToolView] instance
         */
        fun create(view: TextToolView): TextToolPresenter = TextToolPresenterImpl(view)
    }
}

private class TextToolPresenterImpl(
    view: TextToolView
) : BasePresenter<TextToolView>(view), TextToolPresenter {
    private lateinit var tool: TextTool

    override fun onToolBind(tool: TextTool) {
        this.tool = tool
        view.setHint(tool.hint)
        view.setInputDataType(tool.configurationValueType)
        view.setTextValue(tool.store.restore().toString())
    }

    override fun onStoreConfigValue(value: String) {
        when (tool.configurationValueType) {
            String::class -> tool.store.store(value)
            Int::class -> tool.store.store(value.toInt())
            Long::class -> tool.store.store(value.toLong())
            Float::class -> tool.store.store(value.toFloat())
            Double::class -> tool.store.store(value.toDouble())
            else -> throw IllegalArgumentException(
                "${tool.configurationValueType} type not supported"
            )
        }
    }
}
