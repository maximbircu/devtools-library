package com.maximbircu.devtools.android.presentation.tools

import android.content.Context
import com.maximbircu.devtools.android.R
import com.maximbircu.devtools.android.presentation.tool.DevToolLayout
import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.presentation.tool.DevToolView
import com.maximbircu.devtools.common.presentation.tools.group.GroupTool
import com.maximbircu.devtools.common.presentation.tools.group.GroupToolPresenter
import com.maximbircu.devtools.common.presentation.tools.group.GroupToolView
import kotlinx.android.synthetic.main.layout_group_tool.view.devToolsViewsContainer

class GroupToolLayout(context: Context) : DevToolLayout<GroupTool>(context), GroupToolView {
    private val presenter: GroupToolPresenter = GroupToolPresenter.create(this)
    override val layoutRes: Int get() = R.layout.layout_group_tool
    override val devToolsViews: List<DevToolView> get() = devToolsViewsContainer.devToolViews

    override fun onBind(tool: GroupTool) = presenter.onToolBind(tool)

    override fun showTools(tools: List<DevTool<*>>) = devToolsViewsContainer.showDevTools(tools)

    override fun showToolContextMenu() {
        // TODO 36 need show a group tool context menu
    }
}
