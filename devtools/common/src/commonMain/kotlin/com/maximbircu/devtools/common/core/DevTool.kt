package com.maximbircu.devtools.common.core

abstract class DevTool<T>(
    var title: String? = null,
    var description: String = "",
    var canBeDisabled: Boolean = false,
    var defaultEnabledValue: Boolean = true
) {
    val isEnabled: Boolean get() = store.isEnabled

    lateinit var key: String

    abstract fun getDefaultValue(): T

    abstract val store: ToolStore<T>
}
