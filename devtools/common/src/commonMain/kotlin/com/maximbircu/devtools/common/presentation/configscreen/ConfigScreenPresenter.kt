package com.maximbircu.devtools.common.presentation.configscreen

import com.maximbircu.devtools.common.DevTools
import com.maximbircu.devtools.common.core.mvp.BasePresenter
import com.maximbircu.devtools.common.core.mvp.Presenter
import com.maximbircu.devtools.common.presentation.list.DevToolsListView

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
        fun create(
            view: ConfigScreenView,
            devTools: DevTools,
            devToolsList: DevToolsListView
        ): ConfigScreenPresenter {
            return ConfigScreenPresenterImpl(view, devTools, devToolsList)
        }
    }
}

private class ConfigScreenPresenterImpl(
    view: ConfigScreenView,
    private val devTools: DevTools,
    private val devToolsList: DevToolsListView
) : BasePresenter<ConfigScreenView>(view), ConfigScreenPresenter {
    override fun onCreate() {
        devToolsList.showDevTools(devTools.tools.values.toList())
    }

    override fun onApplyConfig() {
        devToolsList.devToolViews.forEach { it.triggerConfigUpdate() }
        devTools.onConfigUpdate.invoke()
    }
}
