package com.maximbircu.devtools.customtool

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.ToolStore

class MyCustomTool : DevTool<Boolean>() {
    override val store: ToolStore<Boolean> = MyCustomToolStore(this)

    override fun getDefaultValue(): Boolean {
        return false // Suppose that I want the default value to always be true.
    }
}
