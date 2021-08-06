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
     * Should be invoked when the configuration screen is closed and destroyed.
     *
     * Rests the in memory dev tools changes.
     */
    fun onDestroy()

    /**
     * Should be invoked when the user wants to apply the dev tools config changes he made.
     *
     * Will notify the [DevTools] to persist the dev tools state.
     */
    fun onApplyConfig()

    companion object {
        fun create(
            view: ConfigScreenView,
            devTools: DevTools,
            devToolsList: DevToolsListView,
            router: ConfigurationScreenRouter?
        ): ConfigScreenPresenter {
            return ConfigScreenPresenterImpl(view, devTools, devToolsList, router)
        }
    }
}

private class ConfigScreenPresenterImpl(
    view: ConfigScreenView,
    private val devTools: DevTools,
    private val devToolsList: DevToolsListView,
    private val router: ConfigurationScreenRouter?
) : BasePresenter<ConfigScreenView>(view), ConfigScreenPresenter {
    override fun onCreate() {
        devToolsList.showDevTools(devTools.tools.values.toList())
        setUpRouter()
    }

    override fun onDestroy() {
        devTools.restorePersistedState()
    }

    override fun onApplyConfig() = devTools.persistToolsState()

    private fun setUpRouter() = router?.run {
        onBack = {
            if (devTools.thereExistUnsavedChanges) {
                view.showConfirmationDialog(::closeScreen)
                true
            } else {
                false
            }
        }
    }
}
