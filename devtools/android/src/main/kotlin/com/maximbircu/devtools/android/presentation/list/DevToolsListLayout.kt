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

class DevToolsListLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAtr: Int = 0
) : RecyclerView(context, attrs, defStyleAtr), DevToolsListView {
    private lateinit var presenter: DevToolsListPresenter
    private val devToolsItems: List<DevToolLayout<*>>
        get() = children().filterIsInstance<DevToolLayout<*>>()

    init {
        layoutManager = LinearLayoutManager(context)
        isFocusableInTouchMode = true
        descendantFocusability = ViewGroup.FOCUS_BEFORE_DESCENDANTS
    }

    fun bind(devTools: List<DevTool<*>>) {
        presenter = DevToolsListPresenter.create(this, devTools)
        presenter.onCreate()
    }

    fun updateConfig() {
        devToolsItems.forEach { it.updateConfig() }
    }

    override fun showDevTools(tools: List<DevTool<*>>) {
        adapter = DevToolsListAdapter(tools)
    }

    fun setDevToolEnabled(isEnabled: Boolean) {
        devToolsItems.forEach { it.setDevToolEnabled(isEnabled) }
    }
}
