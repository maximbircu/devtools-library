package com.maximbircu.devtools.android.presentation.tools.enum.selectors.chips

import android.content.Context
import android.content.res.Resources.NotFoundException
import android.util.AttributeSet
import androidx.core.view.ViewCompat
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.maximbircu.devtools.android.R

internal class ChipGroupLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ChipGroup(context, attrs) {
    private var chips: Map<Int, String> = emptyMap()

    fun setChips(data: List<String>) {
        removeAllViews()
        this.chips = data.map { ViewCompat.generateViewId() to it }.toMap()
        this.chips.forEach { (id, option) -> addView(createChipView(id, option)) }
    }

    fun selectChip(option: String) {
        val chip = chips.entries.find { it.value == option }
        requireNotNull(chip) { NotFoundException("$option not found") }
        check(chip.key)
    }

    fun setOnCheckedChangeListener(onChange: (String) -> Unit) {
        setOnCheckedChangeListener { _, _ -> onChange(chips.getValue(checkedChipId)) }
    }

    fun getCheckedChip(): Chip = findViewById(checkedChipId)

    private fun createChipView(id: Int, text: String): Chip {
        val chip = inflate(context, R.layout.layout_enum_tool_chip, null) as Chip
        chip.id = id
        chip.text = text
        return chip
    }
}
