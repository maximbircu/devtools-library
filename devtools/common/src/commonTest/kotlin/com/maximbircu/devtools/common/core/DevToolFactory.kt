package com.maximbircu.devtools.common.core

import com.maximbircu.devtools.common.utils.mockk
import io.mockk.every

inline fun <T : Any, reified DT : DevTool<T>> createTool(
    receiver: DevToolMokData<T>.() -> Unit = {}
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
    model.storedData?.let { every { store.restore() }.returns(it) }
    every { store.isEnabled }.returns(model.enabled)
}

class DevToolMokData<T : Any> : DevTool<T>() {
    var defaultData: T? = null
    var enabled: Boolean = false
    var storedData: T? = null

    override val store: ToolStore<T> = mockk()

    override fun getDefaultValue(): T {
        return requireNotNull(defaultData) { "Default value was not provided!" }
    }
}
