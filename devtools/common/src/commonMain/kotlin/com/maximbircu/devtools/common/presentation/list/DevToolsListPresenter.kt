package com.maximbircu.devtools.common.presentation.list

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.mvp.BasePresenter
import com.maximbircu.devtools.common.core.mvp.Presenter

interface DevToolsListPresenter : Presenter {
    fun onBind(devtools: List<DevTool<*>>)
    fun onConfigUpdate()

    companion object {
        fun create(view: DevToolsListView): DevToolsListPresenter = DevToolsListPresenterImpl(view)
    }
}

private class DevToolsListPresenterImpl(
    view: DevToolsListView
) : BasePresenter<DevToolsListView>(view), DevToolsListPresenter {
    override fun onBind(devtools: List<DevTool<*>>) = view.showDevTools(devtools)

    override fun onConfigUpdate() {
        view.devToolViews.forEach { it.triggerConfigUpdate() }
    }
}
