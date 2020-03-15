package com.maximbircu.devtools.common.presentation.configscreen

import com.maximbircu.devtools.common.DevTools
import com.maximbircu.devtools.common.core.mvp.BasePresenter
import com.maximbircu.devtools.common.core.mvp.Presenter

interface ConfigScreenPresenter : Presenter {
    fun onCreate()
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
