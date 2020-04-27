package com.maximbircu.devtools.android.extensions

import android.os.Bundle
import com.maximbircu.devtools.common.DevTools
import com.maximbircu.devtools.common.DevToolsParser

/**
 * Updates all dev tools with argument values taken from [bundle].
 * @see [intent arguments](https://developer.android.com/studio/command-line/adb.html#IntentSpec)
 *
 * Please, note that in case the tool you want to update using this method is a child of a
 * [com.maximbircu.devtools.common.presentation.tools.group.GroupTool] then it's key should
 * be prefixed with its parent key according to the logic the
 * [com.maximbircu.devtools.common.core.DevTool.key] is set, i.e `group-tool-key.tool-to-update`
 * @see DevToolsParser.getDevTools
 */
fun DevTools.updateFromBundle(bundle: Bundle?) {
    bundle?.getArguments()?.let(::updateFromParams)
}

private fun Bundle.getArguments(): Map<String, Any> = keySet().map { key ->
    key to requireNotNull(get(key)) { "No value provided for $key" }
}.toMap()
