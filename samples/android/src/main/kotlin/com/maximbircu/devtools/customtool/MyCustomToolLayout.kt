package com.maximbircu.devtools.customtool

import android.content.Context
import com.maximbircu.devtools.R
import com.maximbircu.devtools.android.presentation.tool.DevToolLayout
import kotlinx.android.synthetic.main.layout_my_custom_tool.view.checkbox

class MyCustomToolLayout(context: Context) : DevToolLayout<MyCustomTool>(context) {
    override val layoutRes: Int get() = R.layout.layout_my_custom_tool

    override fun onBind(tool: MyCustomTool) {
        checkbox.isChecked = tool.value
        checkbox.setOnCheckedChangeListener { _, isChecked -> tool.value = isChecked }
    }
}
