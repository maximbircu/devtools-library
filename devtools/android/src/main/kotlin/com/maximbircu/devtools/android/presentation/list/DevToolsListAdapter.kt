package com.maximbircu.devtools.android.presentation.list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maximbircu.devtools.android.DevToolsViewRegistry
import com.maximbircu.devtools.common.core.DevTool

class DevToolsListAdapter(
    private val devtools: List<DevTool<*>>
) : RecyclerView.Adapter<DevToolViewHolder<*>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DevToolViewHolder<*> {
        val viewFactory = DevToolsViewRegistry.getViewFactory(viewType)
        return DevToolViewHolder(viewFactory.invoke(parent.context))
    }

    override fun onBindViewHolder(holder: DevToolViewHolder<*>, position: Int) {
        holder.bind(devtools[position])
    }

    override fun getItemViewType(position: Int): Int {
        return DevToolsViewRegistry.getTypeIndex(devtools[position])
    }

    override fun getItemCount(): Int = devtools.size
}
