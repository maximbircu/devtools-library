package com.maximbircu.devtools.common.presentation.tools.toggle

import com.maximbircu.devtools.common.core.mvp.BaseView

interface ToggleToolView : BaseView {
    fun setValue(value: Boolean)
}
