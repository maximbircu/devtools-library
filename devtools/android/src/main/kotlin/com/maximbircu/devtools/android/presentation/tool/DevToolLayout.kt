package com.maximbircu.devtools.android.presentation.tool

import android.content.Context
import android.widget.RelativeLayout
import com.maximbircu.devtools.android.R
import com.maximbircu.devtools.android.extensions.makeInvisible
import com.maximbircu.devtools.android.extensions.setEnabledRecursively
import com.maximbircu.devtools.android.extensions.show
import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.presentation.tool.DevToolPresenter
import com.maximbircu.devtools.common.presentation.tool.DevToolView
import kotlinx.android.synthetic.main.layout_dev_tool.view.devToolCard
import kotlinx.android.synthetic.main.layout_dev_tool.view.devToolContentContainer
import kotlinx.android.synthetic.main.layout_dev_tool_header.view.toolEnableToggle
import kotlinx.android.synthetic.main.layout_dev_tool_header.view.toolTitle

@Suppress("LeakingThis")
abstract class DevToolLayout<T : DevTool<*>>(
    context: Context
) : RelativeLayout(context), DevToolView {
    abstract val layoutRes: Int
    private val presenter: DevToolPresenter = DevToolPresenter.create(this)

    init {
        inflate(context, R.layout.layout_dev_tool, this)
        inflate(context, layoutRes, devToolContentContainer)
        toolEnableToggle.setOnCheckedChangeListener { _, isEnabled ->
            presenter.onToolEnableToggleUpdated(isEnabled)
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun bind(tool: DevTool<*>) {
        presenter.onToolBind(tool)
        onBind(tool as T)
    }

    abstract fun onBind(tool: T)

    override fun showEnableToggle() = toolEnableToggle.show()

    override fun hideEnableToggle() = toolEnableToggle.makeInvisible()

    override fun setToolEnableState(isEnabled: Boolean) {
        devToolCard.isEnabled = isEnabled
        toolEnableToggle.isChecked = isEnabled
        devToolContentContainer.setEnabledRecursively(isEnabled)
    }

    override fun setTitle(title: String?) {
        toolTitle.text = title
    }
}
