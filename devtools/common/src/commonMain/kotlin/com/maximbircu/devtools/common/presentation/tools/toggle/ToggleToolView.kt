package com.maximbircu.devtools.common.presentation.tools.toggle

import com.maximbircu.devtools.common.core.mvp.BaseView

/**
 * Presents a tool which is able to manipulate boolean configurations to the user.
 */
interface ToggleToolView : BaseView {
    /**
     * Should set the toggle to [value]
     */
    fun setValue(value: Boolean)
}
