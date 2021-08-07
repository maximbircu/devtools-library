package com.maximbircu.devtools.common.presentation.configscreen

import com.maximbircu.devtools.common.DevTools
import com.maximbircu.devtools.common.core.DevTool
import com.maximbircu.devtools.common.core.createTool
import com.maximbircu.devtools.common.mvp.BasePresenterTest
import com.maximbircu.devtools.common.presentation.list.DevToolsListView
import com.maximbircu.devtools.common.utils.mockk
import io.mockk.Called
import io.mockk.every
import io.mockk.slot
import io.mockk.verify
import kotlin.test.Test
import kotlin.test.assertTrue

class ConfigScreenPresenterImplTest :
    BasePresenterTest<ConfigScreenView, ConfigScreenPresenter>(mockk()) {
    private val devTools: DevTools = mockk(relaxed = true)
    private val devToolsList: DevToolsListView = mockk(relaxed = true)
    private val router: ConfigurationScreenRouter = mockk(relaxed = true)

    override fun createPresenter(): ConfigScreenPresenter = createPresenter(router)

    @Test
    fun `shows dev tools on create`() {
        val tools = mapOf<String, DevTool<Boolean>>(
            "first-tool" to createTool(),
            "second-tool" to createTool()
        )
        every { devTools.tools } returns tools

        presenter.onCreate()

        verify { devToolsList.showDevTools(tools.values.toList()) }
    }

    @Test
    fun `restores persisted state on destroy`() {
        presenter.onDestroy()

        verify { devTools.restorePersistedState() }
    }

    @Test
    fun `shows discard dialog if there are unsaved changes`() {
        val router = RouterStub()
        presenter = createPresenter(router)
        val tools = mapOf<String, DevTool<Boolean>>("first-tool" to createTool())
        every { devTools.tools } returns tools
        every { devTools.thereExistUnsavedChanges } returns true
        presenter.onCreate()

        router.onBack?.invoke()

        verify { view.showConfirmationDialog(any()) }
    }

    @Test
    fun `returns false on back if there are no unsaved changes`() {
        val router = RouterStub()
        presenter = createPresenter(router)
        val tools = mapOf<String, DevTool<Boolean>>("first-tool" to createTool())
        every { devTools.tools } returns tools
        every { devTools.thereExistUnsavedChanges } returns false
        presenter.onCreate()

        assertTrue(router.onBack?.invoke() == false)
    }

    @Test
    fun `closes screen on confirmation`() {
        val router = RouterStub()
        presenter = createPresenter(router)
        val tools = mapOf<String, DevTool<Boolean>>("first-tool" to createTool())
        every { devTools.tools } returns tools
        every { devTools.thereExistUnsavedChanges } returns true
        val slot = slot<() -> Unit>()
        every { view.showConfirmationDialog(capture(slot)) } answers { slot.captured.invoke() }
        presenter.onCreate()

        router.onBack?.invoke()

        verify { router.closeScreenListener() }
    }

    @Test
    fun `persists tool state on apply config`() {
        presenter.onApplyConfig()

        devTools.persistToolsState()
    }

    @Test
    fun `doesn't use the router if it was not pass`() {
        val presenter = createPresenter(null)
        presenter.onCreate()

        verify { view wasNot Called }
    }

    private fun createPresenter(router: ConfigurationScreenRouter?): ConfigScreenPresenter {
        return ConfigScreenPresenter.create(view, devTools, devToolsList, router)
    }
}

private class RouterStub : ConfigurationScreenRouter {
    override var onBack: (() -> Boolean)? = null
    val closeScreenListener: () -> Unit = mockk(relaxed = true)

    override fun closeScreen() {
        closeScreenListener()
    }
}
