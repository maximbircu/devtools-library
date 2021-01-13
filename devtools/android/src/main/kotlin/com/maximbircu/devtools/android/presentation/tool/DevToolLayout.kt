package com.maximbircu.devtools.android.presentation.tool

import android.content.Context
import android.view.animation.AnimationUtils.loadAnimation
import android.widget.PopupMenu
import android.widget.RelativeLayout
import com.maximbircu.devtools.android.R
import com.maximbircu.devtools.android.databinding.LayoutDevToolBinding
import com.maximbircu.devtools.android.extensions.inflater
import com.maximbircu.devtools.android.extensions.makeInvisible
import com.maximbircu.devtools.android.extensions.setEnabledRecursively
import com.maximbircu.devtools.android.extensions.setOnClickListener
import com.maximbircu.devtools.android.extensions.show
import com.maximbircu.devtools.android.presentation.tool.help.DevToolHelpDialog
import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.presentation.tool.DevToolPresenter
import com.maximbircu.devtools.common.presentation.tool.DevToolView

@Suppress("LeakingThis")
abstract class DevToolLayout<T : DevTool<*>>(
    context: Context
) : RelativeLayout(context), DevToolView {
    abstract val layoutRes: Int
    private val presenter: DevToolPresenter = DevToolPresenter.create(this)
    protected val binding = LayoutDevToolBinding.inflate(context.inflater, this, true)

    init {
        inflate(context, layoutRes, binding.devToolContentContainer)
        binding.headerContainer.menuButton.setOnClickListener(presenter::onContextMenuButtonClick)
        binding.headerContainer.toolEnableToggle.setOnCheckedChangeListener { _, isEnabled ->
            presenter.onToolEnableToggleUpdated(isEnabled)
        }
        binding.devToolContentContainerOverlay.setOnClickListener {
            binding.headerContainer.toolEnableToggle
                .startAnimation(loadAnimation(context, R.anim.bounce))
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun bind(tool: DevTool<*>) {
        onBind(tool as T)
        presenter.onToolBind(tool)
    }

    abstract fun onBind(tool: T)

    override fun setTitle(title: String?) {
        binding.headerContainer.toolTitle.text = title
    }

    override fun showEnableToggle() = binding.headerContainer.toolEnableToggle.show()

    override fun hideEnableToggle() = binding.headerContainer.toolEnableToggle.makeInvisible()

    override fun setToolEnableState(isEnabled: Boolean) = with(binding) {
        devToolContentContainerOverlay.visibility = if (isEnabled) GONE else VISIBLE
        devToolCard.isEnabled = isEnabled
        headerContainer.toolEnableToggle.isChecked = isEnabled
        devToolContentContainer.setEnabledRecursively(isEnabled)
    }

    override fun showToolContextMenu() {
        val popup = PopupMenu(context, binding.headerContainer.menuButton)
        popup.menuInflater.inflate(R.menu.dev_tool_context_menu, popup.menu)
        popup.setOnMenuItemClickListener(DevToolContextMenuClickListener(presenter))
        popup.show()
    }

    override fun refreshToolData(tool: DevTool<*>) {
        bind(tool)
    }

    override fun showHelpDialog(tool: DevTool<*>) {
        DevToolHelpDialog(context, tool).show()
    }
}
