package com.maximbircu.devtools.common.presentation.tool.help

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.mvp.BasePresenter
import com.maximbircu.devtools.common.core.mvp.Presenter

interface DevToolHelpDialogPresenter : Presenter {

    fun onToolBind(tool: DevTool<*>)

    companion object {
        fun create(view: DevToolHelpDialogView): DevToolHelpDialogPresenter {
            return DevToolHelpDialogPresenterImpl(view)
        }
    }
}

private class DevToolHelpDialogPresenterImpl(
    view: DevToolHelpDialogView
) : BasePresenter<DevToolHelpDialogView>(view), DevToolHelpDialogPresenter {
    override fun onToolBind(tool: DevTool<*>) {
        view.bindDescription(tool.description)
        view.setKey(tool.key)
        view.setCurrentConfigValue(tool.value.toString())
        view.setDefaultConfigValue(tool.getDefaultValue().toString())
    }

    private fun DevToolHelpDialogView.bindDescription(description: String) {
        if (description.isNotBlank()) {
            setDescription(description)
        } else {
            hideDescription()
        }
    }
}
