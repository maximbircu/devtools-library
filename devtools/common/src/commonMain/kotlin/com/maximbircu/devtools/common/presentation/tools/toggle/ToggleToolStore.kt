package com.maximbircu.devtools.common.presentation.tools.toggle

import com.maximbircu.devtools.common.core.ToolStore

class ToggleToolStore : ToolStore<Boolean> {
    override var isEnabled: Boolean = true

    override fun store(value: Boolean) {
        // This class is temporarily, and will be removed as soon as we add a preference impl
    }

    override fun restore(): Boolean = true
}
