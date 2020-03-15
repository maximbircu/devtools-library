package com.maximbircu.devtools.common.presentation.tools.toggle

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.ToolStore

class ToggleTool(private val defaultValue: Boolean = false) : DevTool<Boolean>() {
    override val store: ToolStore<Boolean> = ToggleToolStore()

    override fun getDefaultValue(): Boolean = defaultValue
}
