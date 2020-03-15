package com.maximbircu.devtools.common.presentation.tools.toggle

import com.maximbircu.devtools.common.core.mvp.BasePresenter
import com.maximbircu.devtools.common.core.mvp.Presenter

interface ToggleToolPresenter : Presenter {
    fun onBind(tool: ToggleTool)
    fun onUpdate(data: Boolean)

    companion object {
        fun create(view: ToggleToolView): ToggleToolPresenter {
            return ToggleToolPresenterImpl(view)
        }
    }
}

private class ToggleToolPresenterImpl(
    view: ToggleToolView
) : BasePresenter<ToggleToolView>(view), ToggleToolPresenter {
    private lateinit var tool: ToggleTool

    override fun onBind(tool: ToggleTool) {
        this.tool = tool
        view.setValue(tool.store.restore())
    }

    override fun onUpdate(data: Boolean) {
        tool.store.store(data)
    }
}
