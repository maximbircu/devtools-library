package com.maximbircu.devtools.android.extensions

import android.view.View
import android.view.ViewGroup

internal fun View.show() {
    visibility = View.VISIBLE
}

internal fun View.hide() {
    visibility = View.GONE
}

internal fun ViewGroup.children() = object : Iterable<View> {
    override fun iterator() = object : Iterator<View> {
        var index = 0
        override fun hasNext() = index < childCount
        override fun next() = getChildAt(index++)
    }
}

internal fun View.setOnClickListener(action: () -> Unit) {
    this.setOnClickListener { action() }
}

internal fun View.setEnabledRecursively(isEnabled: Boolean) {
    this.isEnabled = isEnabled
    if (this is ViewGroup) {
        this.children().forEach { it.setEnabledRecursively(isEnabled) }
    }
}
