package com.maximbircu.devtools.common.presentation.tools.group

import com.maximbircu.devtools.common.core.mvp.BasePresenter
import com.maximbircu.devtools.common.core.mvp.Presenter

interface GroupToolPresenter : Presenter {
    fun onToolBind(tool: GroupTool)
    fun onStoreConfigurationValue()

    companion object {
        fun create(view: GroupToolView): GroupToolPresenter {
            return GroupToolPresenterImpl(view)
        }
    }
}

private class GroupToolPresenterImpl(
    view: GroupToolView
) : BasePresenter<GroupToolView>(view), GroupToolPresenter {
    override fun onToolBind(tool: GroupTool) {
        view.showTools(tool.tools.values.toList())
    }

    override fun onStoreConfigurationValue() {
        view.devToolsViews.forEach { toolView -> toolView.persistToolState() }
    }
}
