package com.maximbircu.devtools.android.presentation.list

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.maximbircu.devtools.android.extensions.children
import com.maximbircu.devtools.android.presentation.tool.DevToolLayout
import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.presentation.list.DevToolsListPresenter
import com.maximbircu.devtools.common.presentation.list.DevToolsListView
import com.maximbircu.devtools.common.presentation.tool.DevToolView

internal class DevToolsListLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAtr: Int = 0
) : RecyclerView(context, attrs, defStyleAtr), DevToolsListView {
    private val presenter = DevToolsListPresenter.create(this)
    override val devToolViews: List<DevToolView>
        get() = children().filterIsInstance<DevToolLayout<*>>()

    init {
        layoutManager = LinearLayoutManager(context)
        isFocusableInTouchMode = true
        descendantFocusability = ViewGroup.FOCUS_BEFORE_DESCENDANTS
    }

    fun bind(devTools: List<DevTool<*>>) = presenter.onBind(devTools)

    fun triggerConfigUpdate() = presenter.onConfigUpdate()

    override fun showDevTools(tools: List<DevTool<*>>) {
        adapter = DevToolsListAdapter(tools)
    }
}
