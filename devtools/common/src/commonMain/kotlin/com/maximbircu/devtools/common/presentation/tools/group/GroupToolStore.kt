package com.maximbircu.devtools.common.presentation.tools.group

import com.maximbircu.devtools.common.stores.PreferencesToolStore

/**
 * This store should be used by the [GroupTool].
 * It disables the ability to store/restore configuration values because the [GroupTool] severs just
 * as a component which allows the user to group different tools together and doesn't control any
 * configuration value.
 */
class GroupToolStore : PreferencesToolStore<Unit> {
    /**
     * The group tool should always be enabled while the real enabled state will be handled by
     * its children themselves.
     */
    override var isEnabled: Boolean = true

    /**
     * This action is not supported for [GroupTool] because the tool doesn't have any configuration
     * value, thus we don't have what to store.
     */
    override fun store(value: Unit): Unit = throw UnsupportedOperationException()

    /**
     * This action is not supported for [GroupTool] because the tool doesn't have any configuration
     * value, thus we don't have what to restore.
     */
    override fun restore(): Unit = throw UnsupportedOperationException()
}
