package com.maximbircu.devtools.android.presentation.tools

import android.content.Context
import android.text.InputType.TYPE_CLASS_NUMBER
import android.text.InputType.TYPE_CLASS_TEXT
import android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL
import com.maximbircu.devtools.android.R
import com.maximbircu.devtools.android.presentation.tool.DevToolLayout
import com.maximbircu.devtools.common.presentation.tools.text.TextTool
import com.maximbircu.devtools.common.presentation.tools.text.TextToolPresenter
import com.maximbircu.devtools.common.presentation.tools.text.TextToolView
import kotlinx.android.synthetic.main.layout_text_tool.view.editTextValue
import kotlinx.android.synthetic.main.layout_text_tool.view.inputLayout
import kotlin.reflect.KClass

internal class TextToolLayout(context: Context) : DevToolLayout<TextTool>(context), TextToolView {
    private val presenter = TextToolPresenter.create(this)
    override val layoutRes get() = R.layout.layout_text_tool

    override fun onBind(tool: TextTool) = presenter.onToolBind(tool)

    override fun storeConfigValue() = presenter.onStoreConfigValue(editTextValue.text.toString())

    override fun setHint(hint: String?) {
        inputLayout.hint = hint
    }

    override fun setTextValue(value: String?) {
        editTextValue.setText(value)
    }

    override fun setInputDataType(dataType: KClass<*>) {
        editTextValue.inputType = when (dataType) {
            Float::class, Double::class -> TYPE_NUMBER_FLAG_DECIMAL
            Int::class, Long::class -> TYPE_CLASS_NUMBER
            String::class -> TYPE_CLASS_TEXT
            else -> throw IllegalArgumentException("$dataType not supported")
        }
    }
}
