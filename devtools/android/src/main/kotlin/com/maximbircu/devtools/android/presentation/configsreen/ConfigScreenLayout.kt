package com.maximbircu.devtools.android.presentation.configsreen

import android.annotation.SuppressLint
import android.content.Context
import android.widget.FrameLayout
import com.maximbircu.devtools.android.databinding.LayoutConfigScreenBinding
import com.maximbircu.devtools.android.extensions.inflater
import com.maximbircu.devtools.android.extensions.setOnClickListener
import com.maximbircu.devtools.common.DevTools
import com.maximbircu.devtools.common.presentation.configscreen.ConfigScreenPresenter
import com.maximbircu.devtools.common.presentation.configscreen.ConfigScreenView

@SuppressLint("ViewConstructor")
internal class ConfigScreenLayout(
    context: Context,
    devTools: DevTools
) : FrameLayout(context), ConfigScreenView {
    private val presenter: ConfigScreenPresenter

    init {
        val binding =
            LayoutConfigScreenBinding.inflate(context.inflater, this, true)
        presenter = ConfigScreenPresenter.create(this, devTools, binding.devToolsList)
        presenter.onCreate()
        binding.applyButton.setOnClickListener(presenter::onApplyConfig)
    }
}
