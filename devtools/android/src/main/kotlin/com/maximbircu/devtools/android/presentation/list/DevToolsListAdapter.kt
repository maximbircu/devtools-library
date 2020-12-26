package com.maximbircu.devtools.android.presentation.list

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.maximbircu.devtools.android.DevToolsViewRegistry
import com.maximbircu.devtools.android.presentation.tool.DevToolLayout
import com.maximbircu.devtools.android.presentation.tools.group.GroupToolLayout
import com.maximbircu.devtools.common.core.DevTool

internal class DevToolsListAdapter(
    private val devtools: List<DevTool<*>>
) : RecyclerView.Adapter<ViewHolder>() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewFactory = DevToolsViewRegistry.getViewFactory(viewType)
        val view = viewFactory.invoke(parent.context)
        shareRecycledViewPoolWith(view)
        return DevToolViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder as DevToolViewHolder<*>).bind(devtools[position])
    }

    override fun getItemViewType(position: Int): Int {
        return DevToolsViewRegistry.getTypeIndex(devtools[position])
    }

    override fun getItemCount(): Int = devtools.size

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    private fun shareRecycledViewPoolWith(view: View) {
        if (view is GroupToolLayout) {
            view.devToolsViewsContainer.setRecycledViewPool(recyclerView.recycledViewPool)
        }
    }

    private class DevToolViewHolder<T : DevTool<*>>(view: DevToolLayout<T>) : ViewHolder(view) {
        fun bind(tool: DevTool<*>) = (itemView as DevToolLayout<*>).bind(tool)
    }
}
