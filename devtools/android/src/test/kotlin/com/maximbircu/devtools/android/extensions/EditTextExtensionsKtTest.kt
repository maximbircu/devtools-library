package com.maximbircu.devtools.android.extensions

import android.text.TextWatcher
import android.widget.EditText
import com.maximbircu.devtools.android.BaseTest
import com.maximbircu.devtools.android.utils.mockk
import io.mockk.Called
import io.mockk.every
import io.mockk.mockkConstructor
import io.mockk.slot
import io.mockk.verify
import org.junit.Test

class EditTextExtensionsKtTest : BaseTest() {
    @Test
    fun `sets on text changed listener`() {
        val editText: EditText = mockk()

        editText.onTextChanged { }

        verify { editText.addTextChangedListener(any()) }
    }

    @Test
    fun `notifies listener on text change`() {
        mockkConstructor(TextWatcher::class)
        val listener: (String) -> Unit = mockk(relaxed = true)
        val editText: EditText = mockk()
        val textWatcherSlot = slot<TextWatcher>()
        every { editText.addTextChangedListener(capture(textWatcherSlot)) } answers {
            textWatcherSlot.captured.onTextChanged("Text", 0, 0, 0)
        }

        editText.onTextChanged(listener)

        verify { listener("Text") }
    }

    @Test
    fun `doesn't call the listener after text changed`() {
        mockkConstructor(TextWatcher::class)
        val listener: (String) -> Unit = mockk(relaxed = true)
        val editText: EditText = mockk()
        val textWatcherSlot = slot<TextWatcher>()
        every { editText.addTextChangedListener(capture(textWatcherSlot)) } answers {
            textWatcherSlot.captured.afterTextChanged(mockk())
        }

        editText.onTextChanged(listener)

        verify { listener wasNot Called }
    }

    @Test
    fun `doesn't call the listener before text changed`() {
        mockkConstructor(TextWatcher::class)
        val listener: (String) -> Unit = mockk(relaxed = true)
        val editText: EditText = mockk()
        val textWatcherSlot = slot<TextWatcher>()
        every { editText.addTextChangedListener(capture(textWatcherSlot)) } answers {
            textWatcherSlot.captured.beforeTextChanged("", 0, 0, 0)
        }

        editText.onTextChanged(listener)

        verify { listener wasNot Called }
    }
}
