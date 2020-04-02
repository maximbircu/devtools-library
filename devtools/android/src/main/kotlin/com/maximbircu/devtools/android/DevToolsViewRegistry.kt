package com.maximbircu.devtools.android

import android.content.Context
import com.maximbircu.devtools.android.presentation.tool.DevToolLayout
import com.maximbircu.devtools.android.presentation.tools.TextToolLayout
import com.maximbircu.devtools.android.presentation.tools.toggle.ToggleToolLayout
import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.presentation.tools.text.TextTool
import com.maximbircu.devtools.common.presentation.tools.toggle.ToggleTool
import kotlin.reflect.KClass

object DevToolsViewRegistry {
    private val registry = mutableMapOf<KClass<out DevTool<*>>, (Context) -> DevToolLayout<*>>(
        ToggleTool::class to { context -> ToggleToolLayout(context) },
        TextTool::class to { context -> TextToolLayout(context) }
    )

    fun register(model: KClass<out DevTool<*>>, viewFactory: (Context) -> DevToolLayout<*>) {
        if (registry.containsKey(model)) {
            throw IllegalArgumentException("$model already registered")
        }
        registry[model] = viewFactory
    }

    fun getTypeIndex(model: DevTool<*>): Int {
        val index = registry.keys.indexOf(model::class)
        if (index == -1) {
            throw NullPointerException("$model not registered")
        }
        return index
    }

    fun getViewFactory(type: Int): (Context) -> DevToolLayout<*> {
        return registry.values.toMutableList()[type]
    }
}
