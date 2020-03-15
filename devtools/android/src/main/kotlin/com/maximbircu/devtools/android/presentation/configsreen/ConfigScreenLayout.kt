package com.maximbircu.devtools.android.presentation.configsreen

import android.annotation.SuppressLint
import android.content.Context
import android.widget.FrameLayout
import com.maximbircu.devtools.android.R
import com.maximbircu.devtools.android.extensions.setOnClickListener
import com.maximbircu.devtools.common.DevTools
import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.presentation.configscreen.ConfigScreenPresenter
import com.maximbircu.devtools.common.presentation.configscreen.ConfigScreenView
import kotlinx.android.synthetic.main.layout_config_screen.view.applyButton
import kotlinx.android.synthetic.main.layout_config_screen.view.devToolsList

@SuppressLint("ViewConstructor")
internal class ConfigScreenLayout(
    context: Context,
    devTools: DevTools
) : FrameLayout(context), ConfigScreenView {
    private val presenter = ConfigScreenPresenter.create(this, devTools)

    init {
        inflate(context, R.layout.layout_config_screen, this)
        presenter.onCreate()
        applyButton.setOnClickListener(presenter::onApplyConfig)
    }

    override fun showDevTools(tools: List<DevTool<*>>) = devToolsList.bind(tools)

    override fun triggerConfigUpdate() = devToolsList.updateConfig()
}
