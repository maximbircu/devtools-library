package com.maximbircu.devtools.common.presentation.tools.group

import com.maximbircu.devtools.common.stores.PreferencesToolStore

class GroupToolStore : PreferencesToolStore<Unit> {
    override var isEnabled: Boolean = true

    override fun store(value: Unit): Unit = throw UnsupportedOperationException()

    override fun restore(): Unit = throw UnsupportedOperationException()
}
