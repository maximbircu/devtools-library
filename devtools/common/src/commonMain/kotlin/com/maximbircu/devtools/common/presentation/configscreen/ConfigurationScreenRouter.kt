package com.maximbircu.devtools.common.presentation.configscreen

interface ConfigurationScreenRouter {
    var onBack: (() -> Boolean)?

    fun closeScreen()
}
