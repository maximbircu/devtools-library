package com.maximbircu.devtools.common.presentation.list

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.mvp.BasePresenter
import com.maximbircu.devtools.common.core.mvp.Presenter

interface DevToolsListPresenter : Presenter {
    fun onBind(devtools: List<DevTool<*>>)
    fun onUpdateDevTools()

    companion object {
        fun create(view: DevToolsListView): DevToolsListPresenter {
            return DevToolsListPresenterImpl(view)
        }
    }
}

private class DevToolsListPresenterImpl(
    view: DevToolsListView
) : BasePresenter<DevToolsListView>(view), DevToolsListPresenter {
    override fun onBind(devtools: List<DevTool<*>>) {
        view.showDevTools(devtools)
    }

    override fun onUpdateDevTools() {
        view.updateDevTools()
    }
}
