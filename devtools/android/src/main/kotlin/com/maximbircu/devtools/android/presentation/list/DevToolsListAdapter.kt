package com.maximbircu.devtools.android.presentation.list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.maximbircu.devtools.android.DevToolsViewRegistry
import com.maximbircu.devtools.android.presentation.tool.DevToolLayout
import com.maximbircu.devtools.common.core.DevTool

internal class DevToolsListAdapter(
    private val devtools: List<DevTool<*>>
) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewFactory = DevToolsViewRegistry.getViewFactory(viewType)
        return DevToolViewHolder(viewFactory.invoke(parent.context))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder as DevToolViewHolder<*>).bind(devtools[position])
    }

    override fun getItemViewType(position: Int): Int {
        return DevToolsViewRegistry.getTypeIndex(devtools[position])
    }

    override fun getItemCount(): Int = devtools.size

    private class DevToolViewHolder<T : DevTool<*>>(view: DevToolLayout<T>) : ViewHolder(view) {
        fun bind(tool: DevTool<*>) = (itemView as DevToolLayout<*>).bind(tool)
    }
}
