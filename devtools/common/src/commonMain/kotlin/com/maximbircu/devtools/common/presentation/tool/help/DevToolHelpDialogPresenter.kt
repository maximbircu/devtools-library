package com.maximbircu.devtools.common.presentation.tool.help

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.mvp.BasePresenter
import com.maximbircu.devtools.common.core.mvp.Presenter
import com.maximbircu.devtools.common.presentation.tool.DevToolView

/**
 * Encapsulates the presentation and user interaction logic, which stands behind the [DevToolView].
 */
interface DevToolHelpDialogPresenter : Presenter {
    /**
     * It should be invoked as soon as an [DevTool] instance has been provided to the
     * [DevToolHelpDialogView].
     *
     * @param tool the tool instance provided to the view
     */
    fun onToolBind(tool: DevTool<*>)

    companion object {
        /**
         * Provides a [DevToolHelpDialogPresenter] instance which encapsulates the presentation
         * and user interaction logic of [DevToolHelpDialogView].
         *
         * @param view the [DevToolHelpDialogView] instance which will be used by the presenter
         */
        fun create(view: DevToolHelpDialogView): DevToolHelpDialogPresenter {
            return DevToolHelpDialogPresenterImpl(view)
        }
    }
}

private class DevToolHelpDialogPresenterImpl(
    view: DevToolHelpDialogView
) : BasePresenter<DevToolHelpDialogView>(view), DevToolHelpDialogPresenter {
    override fun onToolBind(tool: DevTool<*>) {
        view.setKey(tool.key)
        view.setCurrentConfigValue(tool.value.toString())
        view.setDefaultConfigValue(tool.getDefaultValue().toString())
        view.bindDescription(tool.description)
        view.bindCriticalUpdateInfo(tool.isCritical)
    }

    private fun DevToolHelpDialogView.bindDescription(description: String) {
        if (description.isNotBlank()) {
            setDescription(description)
        } else {
            hideDescription()
        }
    }

    private fun DevToolHelpDialogView.bindCriticalUpdateInfo(isCriticalUpdate: Boolean) {
        if (isCriticalUpdate) {
            showCriticalUpdateLabel()
        } else {
            hideCriticalUpdateLabel()
        }
    }
}
