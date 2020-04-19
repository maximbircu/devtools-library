package com.maximbircu.devtools.android.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

internal fun EditText.onTextChanged(listener: (text: String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            // We need to trigger the listener just on text changed
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            // We need to trigger the listener just on text changed
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            listener(s.toString())
        }
    })
}
