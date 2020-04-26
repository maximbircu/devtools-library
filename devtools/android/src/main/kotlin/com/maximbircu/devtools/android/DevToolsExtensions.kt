package com.maximbircu.devtools.android

import android.os.Bundle
import com.maximbircu.devtools.common.DevTools

fun DevTools.updateFromBundle(bundle: Bundle?) {
    bundle?.getArguments()?.let(::updateFromParams)
}

private fun Bundle.getArguments(): Map<String, Any> = keySet().map { key ->
    key to requireNotNull(get(key)) { "No value provided for $key" }
}.toMap()
