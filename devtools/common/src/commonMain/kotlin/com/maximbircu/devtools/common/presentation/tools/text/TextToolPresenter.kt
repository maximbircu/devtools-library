package com.maximbircu.devtools.common.presentation.tools.text

import com.maximbircu.devtools.common.core.mvp.BasePresenter
import com.maximbircu.devtools.common.core.mvp.Presenter

interface TextToolPresenter : Presenter {
    fun onToolBind(tool: TextTool)
    fun onStoreConfigValue(value: String)

    companion object {
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
        view.setInputDataType(tool.dataType)
        view.setTextValue(tool.store.restore().toString())
    }

    override fun onStoreConfigValue(value: String) {
        when (tool.dataType) {
            String::class -> tool.store.store(value)
            Int::class -> tool.store.store(value.toInt())
            Long::class -> tool.store.store(value.toLong())
            Float::class -> tool.store.store(value.toFloat())
            Double::class -> tool.store.store(value.toDouble())
            else -> throw IllegalArgumentException("${tool.dataType} type not supported")
        }
    }
}
