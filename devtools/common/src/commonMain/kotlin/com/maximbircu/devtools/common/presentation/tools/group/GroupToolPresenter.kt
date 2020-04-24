package com.maximbircu.devtools.common.presentation.tools.group

import com.maximbircu.devtools.common.core.mvp.BasePresenter
import com.maximbircu.devtools.common.core.mvp.Presenter

interface GroupToolContextMenuPresenter : Presenter {
    fun onEnableAll()
    fun onDisableAll()
    fun onSetAllToDefault()
    fun onResetAllChanges()
}

/**
 * Encapsulates the [GroupTool] business logic and displays group tool members
 * through [GroupToolView].
 */
interface GroupToolPresenter : GroupToolContextMenuPresenter {
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
    private lateinit var tool: GroupTool
    private val tools get() = tool.tools.values

    override fun onToolBind(tool: GroupTool) {
        this.tool = tool
        view.showTools(tool.tools.values.toList())
    }

    override fun onEnableAll() {
        setToolsEnabled(true)
        view.refreshToolData()
    }

    override fun onDisableAll() {
        setToolsEnabled(false)
        view.refreshToolData()
    }

    override fun onSetAllToDefault() {
        tools.forEach { tool -> tool.resetToDefault() }
        view.refreshToolData()
    }

    override fun onResetAllChanges() {
        tools.forEach { tool -> tool.restorePersistedState() }
        view.refreshToolData()
    }

    private fun setToolsEnabled(isEnabled: Boolean) {
        tool.tools.values.forEach { tool ->
            if (tool.canBeDisabled) {
                tool.isEnabled = isEnabled
            }
        }
    }
}
