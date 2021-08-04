package com.maximbircu.devtools.android

import android.view.ViewGroup
import com.maximbircu.devtools.android.presentation.configsreen.ConfigScreenLayout
import com.maximbircu.devtools.common.DevTools
import com.maximbircu.devtools.common.presentation.configscreen.ConfigurationScreenRouter

class DevToolsConfigurationScreen private constructor() {
    companion object {
        fun attachToView(
            viewGroup: ViewGroup,
            devTools: DevTools,
            configScreenRouter: ConfigurationScreenRouter? = null
        ) {
            val devToolsList = ConfigScreenLayout(viewGroup.context, devTools, configScreenRouter)
            viewGroup.addView(devToolsList)
        }
    }
}
