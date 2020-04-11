package com.maximbircu.devtools.android

import android.content.Context
import com.maximbircu.devtools.android.presentation.tool.DevToolLayout
import com.maximbircu.devtools.android.presentation.tools.enum.EnumToolLayout
import com.maximbircu.devtools.android.presentation.tools.text.TextToolLayout
import com.maximbircu.devtools.android.presentation.tools.time.TimeToolLayout
import com.maximbircu.devtools.android.presentation.tools.toggle.ToggleToolLayout
import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.presentation.tools.enum.EnumTool
import com.maximbircu.devtools.common.presentation.tools.text.TextTool
import com.maximbircu.devtools.common.presentation.tools.time.TimeTool
import com.maximbircu.devtools.common.presentation.tools.toggle.ToggleTool
import kotlin.reflect.KClass

object DevToolsViewRegistry {
    private val registry = mutableMapOf<KClass<out DevTool<*>>, (Context) -> DevToolLayout<*>>(
        ToggleTool::class to { context -> ToggleToolLayout(context) },
        TextTool::class to { context -> TextToolLayout(context) },
        TimeTool::class to { context -> TimeToolLayout(context) },
        EnumTool::class to { context -> EnumToolLayout(context) }
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
