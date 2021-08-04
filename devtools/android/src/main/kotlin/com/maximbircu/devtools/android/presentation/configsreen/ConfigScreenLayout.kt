package com.maximbircu.devtools.android.presentation.configsreen

import android.annotation.SuppressLint
import android.content.Context
import android.view.KeyEvent
import android.widget.FrameLayout
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.maximbircu.devtools.android.R.string
import com.maximbircu.devtools.android.databinding.LayoutConfigScreenBinding
import com.maximbircu.devtools.android.extensions.inflater
import com.maximbircu.devtools.android.extensions.setOnClickListener
import com.maximbircu.devtools.common.DevTools
import com.maximbircu.devtools.common.presentation.configscreen.ConfigScreenPresenter
import com.maximbircu.devtools.common.presentation.configscreen.ConfigScreenView

@SuppressLint("ViewConstructor")
internal class ConfigScreenLayout(
    context: Context,
    devTools: DevTools,
    private val closeScreen: (() -> Unit)?
) : FrameLayout(context), ConfigScreenView {
    private val presenter: ConfigScreenPresenter

    init {
        val binding = LayoutConfigScreenBinding.inflate(context.inflater, this, true)
        presenter = ConfigScreenPresenter.create(this, devTools, binding.devToolsList)
        presenter.onCreate()
        binding.applyButton.setOnClickListener(presenter::onApplyConfig)
        closeScreen?.let { setBackButtonListener() }
    }

    override fun showConfirmationDialog(onConfirmed: () -> Unit) {
        MaterialAlertDialogBuilder(context)
            .setPositiveButton(android.R.string.ok) { _, _ -> onConfirmed() }
            .setNegativeButton(android.R.string.cancel, null)
            .setTitle(context.getString(string.config_screen_discard_changes))
            .create()
            .show()
    }

    override fun closeScreen() {
        closeScreen?.invoke()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        presenter.onDestroy()
    }

    private fun setBackButtonListener() {
        isFocusableInTouchMode = true
        requestFocus()
        setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                presenter.onAttemptToGoBack()
                true
            } else {
                false
            }
        }
    }
}
