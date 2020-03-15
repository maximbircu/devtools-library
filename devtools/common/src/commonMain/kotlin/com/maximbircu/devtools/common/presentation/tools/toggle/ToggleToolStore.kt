package com.maximbircu.devtools.common.presentation.tools.toggle

import com.maximbircu.devtools.common.core.ToolStore

class ToggleToolStore : ToolStore<Boolean> {
    override var isEnabled: Boolean = true

    override fun store(value: Boolean) {
    }

    override fun restore(): Boolean = true
}
