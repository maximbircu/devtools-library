package com.maximbircu.devtools.android.extensions

import android.view.View
import android.view.ViewGroup

internal fun View.show() {
    visibility = View.VISIBLE
}

internal fun View.hide() {
    visibility = View.GONE
}

internal fun View.makeInvisible() {
    visibility = View.INVISIBLE
}

internal fun ViewGroup.children(): List<View> = (0 until childCount).map { getChildAt(it) }

internal fun View.setOnClickListener(action: () -> Unit) = this.setOnClickListener { action() }

internal fun View.setEnabledRecursively(isEnabled: Boolean) {
    this.isEnabled = isEnabled
    if (this is ViewGroup) {
        this.children().forEach { it.setEnabledRecursively(isEnabled) }
    }
}
