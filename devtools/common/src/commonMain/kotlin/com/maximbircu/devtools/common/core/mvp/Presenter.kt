package com.maximbircu.devtools.common.core.mvp

interface Presenter

abstract class BasePresenter<out V : BaseView>(val view: V) : Presenter
