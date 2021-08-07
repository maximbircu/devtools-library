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
        @Suppress("UNUSED_PARAMETER")
        set(value) = throw IllegalStateException("Can not enable/disable a group tool")

    override var value: Unit = Unit
        @Suppress("UNUSED_PARAMETER")
        set(value) = throw IllegalStateException("Can not modify a group tool value")
}
