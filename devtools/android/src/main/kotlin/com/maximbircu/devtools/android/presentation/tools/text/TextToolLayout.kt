package com.maximbircu.devtools.android.presentation.tools.text

import android.content.Context
import com.maximbircu.devtools.android.R
import com.maximbircu.devtools.android.databinding.LayoutTextToolBinding
import com.maximbircu.devtools.android.extensions.onTextChanged
import com.maximbircu.devtools.android.presentation.tool.DevToolLayout
import com.maximbircu.devtools.common.presentation.tools.text.TextTool
import com.maximbircu.devtools.common.presentation.tools.text.TextToolPresenter
import com.maximbircu.devtools.common.presentation.tools.text.TextToolView
import kotlin.reflect.KClass

internal class TextToolLayout(context: Context) : DevToolLayout<TextTool>(context), TextToolView {
    private val presenter = TextToolPresenter.create(this)
    private val textToolBinding = LayoutTextToolBinding.bind(this)
    override val layoutRes get() = R.layout.layout_text_tool

    override fun onBind(tool: TextTool) {
        presenter.onToolBind(tool)
        textToolBinding.editTextValue.onTextChanged(presenter::onConfigValueChanged)
    }

    override fun setHint(hint: String?) {
        textToolBinding.inputLayout.hint = hint
    }

    override fun setTextValue(value: String) {
        textToolBinding.editTextValue.setText(value)
    }

    override fun setInputDataType(configurationValueType: KClass<*>) {
        textToolBinding.editTextValue.inputType =
            EditTextInputTypeProvider.getInputTypeFor(configurationValueType)
    }
}
