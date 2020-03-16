package com.maximbircu.devtools.common.presentation.tool

import com.maximbircu.devtools.common.core.mvp.BaseView

/**
 * Should present a dev tool to the user. The tool should have all required controls so that the
 * user will be able to update its state and configuration value.
 */
interface DevToolView : BaseView {
    /**
     * The user should not be able to interact with the tool control if its not enabled.
     *
     * Should provide the tool enable/disable state.
     */
    val isToolEnabled: Boolean

    /**
     * Should display the tool title to the user.
     */
    fun setTitle(title: String?)

    /**
     * Should make the tool enable/disable toggle visible to the user.
     */
    fun showEnableToggle()

    /**
     * Should make the tool enable/disable toggle invisible to the user.
     */
    fun hideEnableToggle()

    /**
     * Should update the tool view such that the user will not be able to access it in case
     * [isEnabled] is false and vice-versa in case its true.
     *
     * @param isEnabled true if the tool should be enabled and false vice-versa
     */
    fun setDevToolEnabled(isEnabled: Boolean)

    /**
     * Should be called when the user triggers a configuration update.
     *
     * The implementation should persist the tool state when the method is invoked.
     */
    fun persistToolState()
}
