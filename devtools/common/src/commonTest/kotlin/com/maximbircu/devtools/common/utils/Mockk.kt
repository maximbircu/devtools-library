package com.maximbircu.devtools.common.utils

import io.mockk.mockk

inline fun <reified T : Any> mockk(relaxed: Boolean = false): T = mockk(
    relaxUnitFun = true,
    relaxed = relaxed
)
