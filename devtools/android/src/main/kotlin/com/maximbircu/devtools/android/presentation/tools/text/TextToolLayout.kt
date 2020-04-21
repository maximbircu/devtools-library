package com.maximbircu.devtools.android.presentation.tools.text

import android.content.Context
import com.maximbircu.devtools.android.R
import com.maximbircu.devtools.android.extensions.onTextChanged
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

    override fun onBind(tool: TextTool) {
        presenter.onToolBind(tool)
        editTextValue.onTextChanged(presenter::onConfigValueChanged)
    }

    override fun setHint(hint: String?) {
        inputLayout.hint = hint
    }

    override fun setTextValue(value: String) {
        editTextValue.setText(value)
    }

    override fun setInputDataType(configurationValueType: KClass<*>) {
        editTextValue.inputType = EditTextInputTypeProvider.getInputTypeFor(configurationValueType)
    }
}
