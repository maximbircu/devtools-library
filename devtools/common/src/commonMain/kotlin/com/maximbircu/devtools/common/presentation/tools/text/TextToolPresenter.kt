package com.maximbircu.devtools.common.presentation.tools.text

import com.maximbircu.devtools.common.core.mvp.BasePresenter
import com.maximbircu.devtools.common.core.mvp.Presenter

/**
 * Encapsulates the [TextTool] business logic and displays text tool data through [TextToolView].
 */
interface TextToolPresenter : Presenter {
    /**
     * Should be called as soon as a [TextTool] instance has been provided to the [TextToolView].
     *
     * @param tool the tool instance provided to the view
     */
    fun onToolBind(tool: TextTool)

    /**
     * Should be called whenever the configuration value is changed.
     *
     * @param value the new configuration value
     */
    fun onConfigValueChanged(value: String)

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
        view.setTextValue(tool.value.toString())
    }

    override fun onConfigValueChanged(value: String) {
        when (tool.configurationValueType) {
            String::class -> tool.value = value
            Int::class -> tool.value = value.toInt()
            Long::class -> tool.value = value.toLong()
            Float::class -> tool.value = value.toFloat()
            Double::class -> tool.value = value.toDouble()
            else -> throw IllegalArgumentException(
                "${tool.configurationValueType} type not supported"
            )
        }
    }
}
