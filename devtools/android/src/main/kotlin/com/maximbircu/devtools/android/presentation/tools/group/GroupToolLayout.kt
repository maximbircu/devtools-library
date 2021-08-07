package com.maximbircu.devtools.android.presentation.tools.group

import android.annotation.SuppressLint
import android.content.Context
import android.widget.PopupMenu
import com.maximbircu.devtools.android.R
import com.maximbircu.devtools.android.databinding.LayoutGroupToolBinding
import com.maximbircu.devtools.android.presentation.list.DevToolsListLayout
import com.maximbircu.devtools.android.presentation.tool.DevToolLayout
import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.presentation.tool.DevToolView
import com.maximbircu.devtools.common.presentation.tools.group.GroupTool
import com.maximbircu.devtools.common.presentation.tools.group.GroupToolPresenter
import com.maximbircu.devtools.common.presentation.tools.group.GroupToolView

class GroupToolLayout(context: Context) : DevToolLayout<GroupTool>(context), GroupToolView {
    private val presenter: GroupToolPresenter = GroupToolPresenter.create(this)
    private val groupToolBinding = LayoutGroupToolBinding.bind(this)
    internal val devToolsViewsContainer: DevToolsListLayout
        get() = groupToolBinding.devToolsViewsContainer
    override val devToolsViews: List<DevToolView>
        get() = devToolsViewsContainer.devToolViews
    override val layoutRes: Int get() = R.layout.layout_group_tool

    override fun onBind(tool: GroupTool) = presenter.onToolBind(tool)

    override fun showTools(tools: List<DevTool<*>>) {
        devToolsViewsContainer.showDevTools(tools)
    }

    override fun showToolContextMenu() {
        val popup = PopupMenu(context, binding.headerContainer.menuButton)
        popup.menuInflater.inflate(R.menu.group_tool_context_menu, popup.menu)
        popup.setOnMenuItemClickListener(GroupToolContextMenuClickListener(presenter))
        popup.show()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun refreshToolData() {
        devToolsViewsContainer.adapter?.notifyDataSetChanged()
    }
}
