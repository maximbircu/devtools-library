package com.maximbircu.devtools.android.extensions

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children

internal fun View.show() {
    visibility = View.VISIBLE
}

internal fun View.hide() {
    visibility = View.GONE
}

internal fun View.makeInvisible() {
    visibility = View.INVISIBLE
}

internal fun View.setOnClickListener(action: () -> Unit) = this.setOnClickListener { action() }

internal fun View.setEnabledRecursively(isEnabled: Boolean) {
    this.isEnabled = isEnabled
    if (this is ViewGroup) {
        this.children.forEach { it.setEnabledRecursively(isEnabled) }
    }
}
