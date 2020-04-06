package com.maximbircu.devtools.common.utils

import io.mockk.every
import io.mockk.mockk

inline fun <reified T : Any> mockk(relaxed: Boolean = false): T = mockk(
    relaxUnitFun = true,
    relaxed = relaxed
)

infix fun <T> (() -> T?).returns(data: T) {
    every { this@returns.invoke() } returns data
}
