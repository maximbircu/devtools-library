package com.maximbircu.devtools.android.presentation.configsreen

import android.annotation.SuppressLint
import android.content.Context
import android.widget.FrameLayout
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.maximbircu.devtools.android.R.string
import com.maximbircu.devtools.android.databinding.LayoutConfigScreenBinding
import com.maximbircu.devtools.android.extensions.inflater
import com.maximbircu.devtools.android.extensions.setOnClickListener
import com.maximbircu.devtools.common.DevTools
import com.maximbircu.devtools.common.presentation.configscreen.ConfigScreenPresenter
import com.maximbircu.devtools.common.presentation.configscreen.ConfigScreenView
import com.maximbircu.devtools.common.presentation.configscreen.ConfigurationScreenRouter

@SuppressLint("ViewConstructor")
internal class ConfigScreenLayout(
    context: Context,
    devTools: DevTools,
    router: ConfigurationScreenRouter?
) : FrameLayout(context), ConfigScreenView {
    private val presenter: ConfigScreenPresenter

    init {
        val binding = LayoutConfigScreenBinding.inflate(context.inflater, this, true)
        presenter = ConfigScreenPresenter.create(this, devTools, binding.devToolsList, router)
        presenter.onCreate()
        binding.applyButton.setOnClickListener(presenter::onApplyConfig)
    }

    override fun showConfirmationDialog(onConfirmed: () -> Unit) {
        MaterialAlertDialogBuilder(context)
            .setPositiveButton(android.R.string.ok) { _, _ -> onConfirmed() }
            .setNegativeButton(android.R.string.cancel, null)
            .setTitle(context.getString(string.config_screen_discard_changes))
            .create()
            .show()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        presenter.onDestroy()
    }
}
