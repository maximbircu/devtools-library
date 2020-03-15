package com.maximbircu.devtools.android

import android.view.ViewGroup
import com.maximbircu.devtools.android.presentation.configsreen.ConfigScreenLayout
import com.maximbircu.devtools.common.DevTools

class DevToolsConfigurationScreen  {
    companion object {
        fun attachToView(viewGroup: ViewGroup, devTools: DevTools) {
            val devToolsList = ConfigScreenLayout(viewGroup.context, devTools)
            viewGroup.addView(devToolsList)
        }
    }
}
