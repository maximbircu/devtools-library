package com.maximbircu.devtools.android.presentation.list

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.maximbircu.devtools.android.presentation.tool.DevToolLayout
import com.maximbircu.devtools.common.core.DevTool

class DevToolViewHolder<T : DevTool<*>>(view: DevToolLayout<T>) : ViewHolder(view) {
    fun bind(tool: DevTool<*>) {
        (itemView as DevToolLayout<*>).bind(tool)
    }
}
