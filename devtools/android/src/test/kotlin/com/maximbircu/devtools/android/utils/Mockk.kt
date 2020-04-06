package com.maximbircu.devtools.android.utils

import io.mockk.mockk

inline fun <reified T : Any> mockk(relaxed: Boolean = false): T = mockk(
    relaxUnitFun = true,
    relaxed = relaxed
)
