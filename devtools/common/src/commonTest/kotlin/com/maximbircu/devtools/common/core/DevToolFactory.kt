package com.maximbircu.devtools.common.core

import com.maximbircu.devtools.common.utils.mockk
import io.mockk.every

inline fun <reified T : DevTool<*>> createTool(mock: T.() -> Unit = {}): T {
    val tool = mockk<T>(relaxed = true)

    every { tool.title } returns null
    every { tool.description } returns ""
    every { tool.canBeDisabled } returns false
    every { tool.isEnabled } returns true

    mock(tool)
    return tool
}
