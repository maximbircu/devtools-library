package com.maximbircu.devtools.android.presentation.tool.help

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.maximbircu.devtools.android.R
import kotlinx.android.synthetic.main.layout_dev_tool_help_dialog_info_row.view.helpRowTitle
import kotlinx.android.synthetic.main.layout_dev_tool_help_dialog_info_row.view.helpRowValue

class DevToolHelpDialogInfoRowLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    var value: String = ""
        set(value) {
            field = value
            helpRowValue.text = value
        }

    init {
        inflate(context, R.layout.layout_dev_tool_help_dialog_info_row, this)
        val array = context.obtainStyledAttributes(
            attrs,
            R.styleable.DevToolHelpDialogInfoRowLayout,
            defStyleAttr,
            0
        )
        try {
            helpRowTitle.text = array.getString(
                R.styleable.DevToolHelpDialogInfoRowLayout_title
            ) ?: ""
        } finally {
            array.recycle()
        }
    }
}
