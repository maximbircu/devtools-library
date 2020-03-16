package com.maximbircu.devtools.common.presentation.configscreen

import com.maximbircu.devtools.common.DevTools
import com.maximbircu.devtools.common.core.mvp.BasePresenter
import com.maximbircu.devtools.common.core.mvp.Presenter

interface ConfigScreenPresenter : Presenter {
    /**
     * Should be invoked when the screen is displayed to the user.
     *
     * Initializes the configuration screen and shows a list of dev tools to the user.
     */
    fun onCreate()

    /**
     * Should be invoked when the user wants to apply the dev tools changes he made.
     *
     * Triggers a global dev tools update and makes each tool persist its currently selected value.
     */
    fun onApplyConfig()


    companion object {
        fun create(view: ConfigScreenView, devTools: DevTools): ConfigScreenPresenter {
            return ConfigScreenPresenterImpl(view, devTools)
        }
    }
}

private class ConfigScreenPresenterImpl(
    view: ConfigScreenView,
    private val devTools: DevTools
) : BasePresenter<ConfigScreenView>(view), ConfigScreenPresenter {
    override fun onCreate() {
        view.showDevTools(devTools.tools.values.toList())
    }

    override fun onApplyConfig() {
        view.triggerConfigUpdate()
        devTools.onConfigUpdate.invoke()
    }
}
