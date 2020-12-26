package com.maximbircu.devtools.android.presentation.tool.help

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.core.content.withStyledAttributes
import com.maximbircu.devtools.android.R
import com.maximbircu.devtools.android.databinding.LayoutDevToolHelpDialogInfoRowBinding
import com.maximbircu.devtools.android.extensions.inflater

class DevToolHelpDialogInfoRowLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    var value: String = ""
        set(value) {
            field = value
            binding.helpRowTitle.text = value
        }

    private val binding =
        LayoutDevToolHelpDialogInfoRowBinding.inflate(context.inflater, this, true)

    init {
        context.withStyledAttributes(attrs, R.styleable.DevToolHelpDialogInfoRowLayout) {
            binding.helpRowTitle.text =
                getString(R.styleable.DevToolHelpDialogInfoRowLayout_title) ?: ""
        }
    }
}
