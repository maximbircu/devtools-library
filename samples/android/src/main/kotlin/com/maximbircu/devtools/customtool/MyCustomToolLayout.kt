package com.maximbircu.devtools.customtool

import android.content.Context
import com.maximbircu.devtools.R
import com.maximbircu.devtools.android.presentation.tool.DevToolLayout
import com.maximbircu.devtools.databinding.LayoutMyCustomToolBinding

class MyCustomToolLayout(context: Context) : DevToolLayout<MyCustomTool>(context) {
    override val layoutRes: Int get() = R.layout.layout_my_custom_tool
    private val myCustomBinding = LayoutMyCustomToolBinding.bind(this)

    override fun onBind(tool: MyCustomTool) {
        myCustomBinding.checkbox.isChecked = tool.value
        myCustomBinding.checkbox.setOnCheckedChangeListener { _, isChecked ->
            tool.value = isChecked
        }
    }
}
