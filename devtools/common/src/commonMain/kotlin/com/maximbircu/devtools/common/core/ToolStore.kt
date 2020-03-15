package com.maximbircu.devtools.common.core

interface ToolStore<T> {
    var isEnabled: Boolean

    fun store(value: T)

    fun restore(): T
}
