package com.maximbircu.devtools.android

import android.view.ViewGroup
import com.maximbircu.devtools.android.presentation.configsreen.ConfigScreenLayout
import com.maximbircu.devtools.common.DevTools
import com.maximbircu.devtools.common.presentation.configscreen.ConfigurationScreenRouter

class DevToolsConfigurationScreen private constructor() {
    companion object {
        /**
         * Use this method to display a dynamically generated configuration screen that will allow
         * the user to update the dev tools values.
         *
         * @param viewGroup the root view to which the config screen view will be attached
         * @param devTools the instance of [DevTools] class to be configurable by this screen
         * @param configScreenRouter (optional) an interface that will allow configuration screen
         *      implement navigation logic - provide this just in case you want to see discard
         *      dialog when trying to close the screen with unsaved changes
         */
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
