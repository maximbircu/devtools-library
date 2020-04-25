package com.maximbircu.devtools.android.presentation.tools.group

import android.content.Context
import android.widget.PopupMenu
import com.maximbircu.devtools.android.R
import com.maximbircu.devtools.android.presentation.tool.DevToolLayout
import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.presentation.tool.DevToolView
import com.maximbircu.devtools.common.presentation.tools.group.GroupTool
import com.maximbircu.devtools.common.presentation.tools.group.GroupToolPresenter
import com.maximbircu.devtools.common.presentation.tools.group.GroupToolView
import kotlinx.android.synthetic.main.layout_dev_tool_header.view.menuButton
import kotlinx.android.synthetic.main.layout_group_tool.view.devToolsViewsContainer

class GroupToolLayout(context: Context) : DevToolLayout<GroupTool>(context), GroupToolView {
    private val presenter: GroupToolPresenter = GroupToolPresenter.create(this)
    override val layoutRes: Int get() = R.layout.layout_group_tool
    override val devToolsViews: List<DevToolView> get() = devToolsViewsContainer.devToolViews

    override fun onBind(tool: GroupTool) = presenter.onToolBind(tool)

    override fun showTools(tools: List<DevTool<*>>) = devToolsViewsContainer.showDevTools(tools)

    override fun showToolContextMenu() {
        val popup = PopupMenu(context, menuButton)
        popup.menuInflater.inflate(R.menu.group_tool_context_menu, popup.menu)
        popup.setOnMenuItemClickListener(GroupToolContextMenuClickListener(presenter))
        popup.show()
    }

    override fun refreshToolData() {
        devToolsViewsContainer.adapter?.notifyDataSetChanged()
    }
}
