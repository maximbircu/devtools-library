package com.maximbircu.devtools.android

import android.view.ViewGroup
import com.maximbircu.devtools.android.presentation.configsreen.ConfigScreenLayout
import com.maximbircu.devtools.common.DevTools

class DevToolsConfigurationScreen private constructor() {
    companion object {
        fun attachToView(
            viewGroup: ViewGroup,
            devTools: DevTools,
            closeScreen: (() -> Unit)? = null
        ) {
            val devToolsList = ConfigScreenLayout(viewGroup.context, devTools, closeScreen)
            viewGroup.addView(devToolsList)
        }
    }
}
