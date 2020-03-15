package com.maximbircu.devtools.common.presentation.tools.toggle.utils

import io.mockk.mockk

inline fun <reified T : Any> mockk(): T = mockk(relaxUnitFun = true)
