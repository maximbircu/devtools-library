package com.maximbircu.devtools.common.presentation.list

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.mvp.BasePresenter
import com.maximbircu.devtools.common.core.mvp.Presenter

interface DevToolsListPresenter : Presenter {
    fun onCreate()
    fun onUpdateDevTools()

    companion object {
        fun create(
            view: DevToolsListView,
            devtools: List<DevTool<*>>
        ): DevToolsListPresenter {
            return DevToolsListPresenterImpl(view, devtools)
        }
    }
}

private class DevToolsListPresenterImpl(
    view: DevToolsListView,
    private val devtools: List<DevTool<*>>
) : BasePresenter<DevToolsListView>(view), DevToolsListPresenter {
    override fun onCreate() {
        view.showDevTools(devtools)
    }

    override fun onUpdateDevTools() {
//        view.notifyConfigUpdate()
    }
}
