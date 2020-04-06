package com.maximbircu.devtools.android.presentation.tools.text

import android.text.InputType.TYPE_CLASS_NUMBER
import android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL
import android.text.InputType.TYPE_CLASS_TEXT
import com.maximbircu.devtools.android.BaseTest
import org.junit.Test
import kotlin.test.assertEquals

class EditTextInputTypeProviderTest: BaseTest() {
    @Test
    fun `returns text input type for string`() {
        val inputType = EditTextInputTypeProvider.getInputTypeFor(String::class)

        assertEquals(TYPE_CLASS_TEXT, inputType)
    }

    @Test
    fun `returns number input type for int`() {
        val inputType = EditTextInputTypeProvider.getInputTypeFor(Int::class)

        assertEquals(TYPE_CLASS_NUMBER, inputType)
    }

    @Test
    fun `returns number input type for long`() {
        val inputType = EditTextInputTypeProvider.getInputTypeFor(Long::class)

        assertEquals(TYPE_CLASS_NUMBER, inputType)
    }

    @Test
    fun `returns decimal input type for float`() {
        val inputType = EditTextInputTypeProvider.getInputTypeFor(Float::class)

        assertEquals(TYPE_NUMBER_FLAG_DECIMAL, inputType)
    }

    @Test
    fun `returns decimal input type for double`() {
        val inputType = EditTextInputTypeProvider.getInputTypeFor(Double::class)

        assertEquals(TYPE_NUMBER_FLAG_DECIMAL, inputType)
    }
}
