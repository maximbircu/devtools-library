package com.maximbircu.devtools.common

import android.content.SharedPreferences
import com.maximbircu.devtools.common.mvp.BaseTest
import com.maximbircu.devtools.common.utils.mockk
import io.mockk.every
import io.mockk.verify
import org.junit.Test
import java.lang.Double.doubleToRawLongBits
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class SharedPreferencesExtensionsTest : BaseTest() {
    private val sharedPrefsEditor = mockk<SharedPreferences.Editor>(relaxed = true)
    private val sharedPrefs = mockk<SharedPreferences>(relaxed = true)

    init {
        every { sharedPrefsEditor.putBoolean(any(), any()) } returns sharedPrefsEditor
        every { sharedPrefs.edit() } returns sharedPrefsEditor
    }

    // Store methods Editor.put(key: String?, value: T)

    @Test
    fun `stores int value properly`() {
        sharedPrefsEditor.put("tool-key", 1)

        verify { sharedPrefsEditor.putInt("tool-key", 1) }
    }

    @Test
    fun `stores long value properly`() {
        sharedPrefsEditor.put("tool-key", 2L)

        verify { sharedPrefsEditor.putLong("tool-key", 2) }
    }

    @Test
    fun `stores string value properly`() {
        sharedPrefsEditor.put("tool-key", "string value")

        verify { sharedPrefsEditor.putString("tool-key", "string value") }
    }

    @Test
    fun `stores float value properly`() {
        sharedPrefsEditor.put("tool-key", 3f)

        verify { sharedPrefsEditor.putFloat("tool-key", 3f) }
    }

    @Test
    fun `stores boolean value properly`() {
        sharedPrefsEditor.put("tool-key", true)

        verify { sharedPrefsEditor.putBoolean("tool-key", true) }
    }

    @Test
    fun `throws exception when trying to store a value of not supported type`() {
        val unsupportedType = object {}

        assertFailsWith(IllegalArgumentException::class) {
            sharedPrefsEditor.put("tool-key", unsupportedType)
        }
    }

    // Restore methods SharedPreferences.get(key: String?, defaultValue: T)

    @Test
    fun `restore int value properly`() {
        sharedPrefs.get("tool-key", 3)

        verify { sharedPrefs.getInt("tool-key", 3) }
    }

    @Test
    fun `restore long value properly`() {
        sharedPrefs.get("tool-key", 3L)

        verify { sharedPrefs.getLong("tool-key", 3L) }
    }

    @Test
    fun `restore string value properly`() {
        sharedPrefs.get("tool-key", "string value")

        verify { sharedPrefs.getString("tool-key", "string value") }
    }

    @Test
    fun `restore float value properly`() {
        sharedPrefs.get("tool-key", 3f)

        verify { sharedPrefs.getFloat("tool-key", 3f) }
    }

    @Test
    fun `restore boolean value properly`() {
        sharedPrefs.get("tool-key", false)

        verify { sharedPrefs.getBoolean("tool-key", false) }
    }

    @Test
    fun `restore double value properly`() {
        sharedPrefs.get("tool-key", 3.0)

        verify { sharedPrefs.getDouble("tool-key", 3.0) }
    }

    @Test
    fun `throws exception when trying to restore value of not supported type`() {
        val unsupportedType = object {}

        assertFailsWith(IllegalArgumentException::class) {
            sharedPrefs.get("tool-key", unsupportedType)
        }
    }

    // Restore/Put double

    @Test
    fun `stores double value properly`() {
        sharedPrefsEditor.put("tool-key", 5.0)

        verify { sharedPrefsEditor.putDouble("tool-key", 5.0) }
    }

    @Test
    fun `saves double value properly`() {
        sharedPrefsEditor.putDouble("radio-tool", 30.0)

        verify { sharedPrefsEditor.putLong("radio-tool", doubleToRawLongBits(30.0)) }
    }

    @Test
    fun `restores double value properly if the key exists and it was stored`() {
        every { sharedPrefs.contains("radio-tool") } returns true
        every { sharedPrefs.all } returns mapOf("radio-tool" to doubleToRawLongBits(25.3))

        val actualValue = sharedPrefs.getDouble("radio-tool", 30.0)

        assertEquals(25.3, actualValue)
    }

    @Test
    fun `returns default value if the key doesn't exist and no value was stored`() {
        every { sharedPrefs.contains("radio-tool") } returns false

        val actualValue = sharedPrefs.getDouble("radio-tool", 30.0)

        assertEquals(30.0, actualValue)
    }
}
