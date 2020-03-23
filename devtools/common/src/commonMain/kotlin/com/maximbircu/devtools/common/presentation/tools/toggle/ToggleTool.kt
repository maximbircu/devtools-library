package com.maximbircu.devtools.common.presentation.tools.toggle

import com.maximbircu.devtools.common.core.PreferencesDevTool

class ToggleTool(private val defaultValue: Boolean = false) : PreferencesDevTool<Boolean>() {
    override fun getDefaultValue(): Boolean = defaultValue
}
