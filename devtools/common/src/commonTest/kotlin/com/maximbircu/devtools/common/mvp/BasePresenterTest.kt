package com.maximbircu.devtools.common.mvp

import com.maximbircu.devtools.common.core.mvp.BaseView
import com.maximbircu.devtools.common.core.mvp.Presenter
import kotlin.test.BeforeTest

abstract class BasePresenterTest<V : BaseView, P : Presenter>(protected val view: V) : BaseTest() {
    protected lateinit var presenter: P

    abstract fun createPresenter(): P

    @BeforeTest
    fun setUp() {
        presenter = createPresenter()
    }
}
