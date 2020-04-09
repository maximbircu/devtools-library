package com.maximbircu.devtools.common.presentation.tools.time

import com.maximbircu.devtools.common.mvp.BaseTest
import kotlin.test.Test
import kotlin.test.assertEquals

class TimeTest : BaseTest() {
    @Test
    fun `initializes proper time object from duration as number`() {
        val time = Time(duration = 93784005)

        assertEquals(1, time.days)
        assertEquals(2, time.hours)
        assertEquals(3, time.minutes)
        assertEquals(4, time.seconds)
        assertEquals(5, time.milliseconds)
    }

    @Test
    fun `initializes proper time object from duration as text`() {
        val time = Time(duration = "1d 2h 3m 4s 5ms")

        assertEquals(1, time.days)
        assertEquals(2, time.hours)
        assertEquals(3, time.minutes)
        assertEquals(4, time.seconds)
        assertEquals(5, time.milliseconds)
    }

    @Test
    fun `converts properly time object to duration in milliseconds`() {
        val time = Time(days = 1, hours = 2, minutes = 3, seconds = 4, milliseconds = 5)

        assertEquals(93784005, time.inMilliseconds())
    }

    @Test
    fun `converts properly time object to time text`() {
        val time = Time(days = 1, hours = 2, minutes = 3, seconds = 4, milliseconds = 5)

        assertEquals("1d 2h 3m 4s 5ms", time.toString())
    }
}
