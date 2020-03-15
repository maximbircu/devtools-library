package com.maximbircu.devtools.common.presentation.tool

import com.maximbircu.devtools.common.core.mvp.BaseView

interface DevToolView : BaseView {
    val isToolEnabled: Boolean

    fun setTitle(title: String?)
    fun showEnableToggle()
    fun hideEnableToggle()
    fun setDevToolEnabled(isEnabled: Boolean)
}
