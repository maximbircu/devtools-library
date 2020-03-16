package com.maximbircu.devtools.android.presentation.tool

import android.content.Context
import android.widget.RelativeLayout
import androidx.annotation.CallSuper
import com.maximbircu.devtools.android.R
import com.maximbircu.devtools.android.extensions.hide
import com.maximbircu.devtools.android.extensions.setEnabledRecursively
import com.maximbircu.devtools.android.extensions.show
import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.presentation.tool.DevToolPresenter
import com.maximbircu.devtools.common.presentation.tool.DevToolView
import kotlinx.android.synthetic.main.layout_dev_tool.view.devToolContentContainer
import kotlinx.android.synthetic.main.layout_dev_tool_header.view.toolEnableToggle
import kotlinx.android.synthetic.main.layout_dev_tool_header.view.toolTitle

@Suppress("LeakingThis")
abstract class DevToolLayout<T : DevTool<*>>(
    context: Context
) : RelativeLayout(context), DevToolView {
    abstract val layoutRes: Int
    private val presenter: DevToolPresenter = DevToolPresenter.create(this)
    override val isToolEnabled: Boolean get() = toolEnableToggle.isChecked

    init {
        inflate(context, R.layout.layout_dev_tool, this)
        inflate(context, layoutRes, devToolContentContainer)
    }

    @Suppress("UNCHECKED_CAST")
    fun bind(tool: DevTool<*>) {
        onBind(tool as T)
        presenter.onToolBind(tool)
        toolEnableToggle.setOnCheckedChangeListener { _, isEnabled ->
            presenter.onToolEnableToggleUpdated(isEnabled)
        }
    }

    @CallSuper
    override fun persistToolState() {
        presenter.onPersistToolState()
        storeConfigValue()
    }

    abstract fun storeConfigValue()

    abstract fun onBind(tool: T)

    override fun showEnableToggle() = toolEnableToggle.show()

    override fun hideEnableToggle() = toolEnableToggle.hide()

    override fun setDevToolEnabled(isEnabled: Boolean) {
        this.isEnabled = isEnabled
        toolEnableToggle.isChecked = isEnabled
        devToolContentContainer.setEnabledRecursively(isEnabled)
    }

    override fun setTitle(title: String?) {
        toolTitle.text = title
    }
}
