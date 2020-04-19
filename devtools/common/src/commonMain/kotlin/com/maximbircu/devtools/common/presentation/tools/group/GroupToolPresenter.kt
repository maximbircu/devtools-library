package com.maximbircu.devtools.common.presentation.tools.group

import com.maximbircu.devtools.common.core.mvp.BasePresenter
import com.maximbircu.devtools.common.core.mvp.Presenter

/**
 * Encapsulates the [GroupTool] business logic and displays group tool members
 * through [GroupToolView].
 */
interface GroupToolPresenter : Presenter {
    /**
     * Should be called as soon as a [GroupTool] instance has been provided to the [GroupToolView].
     *
     * @param tool the tool instance provided to the view
     */
    fun onToolBind(tool: GroupTool)

    companion object {
        /**
         * Provides a new [GroupToolPresenter] instance.
         *
         * @param view a [GroupToolView] instance
         */
        fun create(view: GroupToolView): GroupToolPresenter = GroupToolPresenterImpl(view)
    }
}

private class GroupToolPresenterImpl(
    view: GroupToolView
) : BasePresenter<GroupToolView>(view), GroupToolPresenter {
    override fun onToolBind(tool: GroupTool) {
        view.showTools(tool.tools.values.toList())
    }
}
