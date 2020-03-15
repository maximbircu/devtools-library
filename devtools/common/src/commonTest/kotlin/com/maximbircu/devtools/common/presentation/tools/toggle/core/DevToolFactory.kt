package com.maximbircu.devtools.common.presentation.tools.toggle.core

import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.ToolStore
import com.maximbircu.devtools.common.presentation.tools.toggle.utils.mockk
import io.mockk.every

inline fun <T : Any, reified DT : DevTool<T>> createTool(
    receiver: DevToolMokData<T>.() -> Unit
): DT {
    val devToolMokModel = DevToolMokData<T>()
    receiver(devToolMokModel)
    val tool = mockk<DT>()
    tool.mockWith(devToolMokModel)
    return tool
}

fun <T : Any> DevTool<T>.mockWith(model: DevToolMokData<T>) {
    every { title }.returns(model.title)
    every { description }.returns(model.description)
    every { canBeDisabled }.returns(model.canBeDisabled)
    every { defaultEnabledValue }.returns(model.defaultEnabledValue)
    every { store }.returns(model.store)
    every { store.restore() }.returns(model.storedData)
    every { store.isEnabled }.returns(model.enabled)
}

class DevToolMokData<T : Any> : DevTool<T>() {
    var defaultData: T? = null
    var enabled: Boolean = false
    lateinit var storedData: T

    override val store: ToolStore<T> = mockk()

    override fun getDefaultValue(): T {
        return requireNotNull(defaultData) { "Default value was not provided!" }
    }
}
