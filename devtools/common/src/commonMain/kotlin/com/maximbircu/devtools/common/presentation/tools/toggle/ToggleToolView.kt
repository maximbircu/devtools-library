package com.maximbircu.devtools.common.presentation.tools.toggle

import com.maximbircu.devtools.common.core.mvp.BaseView

/**
 * Presents a dev tool which is able to manipulate boolean configuration values.
 */
interface ToggleToolView : BaseView {
    /**
     * Should present the current configuration value.
     *
     * @param value current configuration value
     */
    fun setValue(value: Boolean)
}
