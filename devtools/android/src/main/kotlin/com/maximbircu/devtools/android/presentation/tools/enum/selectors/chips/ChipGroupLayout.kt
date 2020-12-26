package com.maximbircu.devtools.android.presentation.tools.enum.selectors.chips

import android.content.Context
import android.content.res.Resources.NotFoundException
import android.util.AttributeSet
import androidx.core.view.ViewCompat
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.maximbircu.devtools.android.databinding.LayoutEnumToolChipBinding
import com.maximbircu.devtools.android.extensions.inflater

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
        setOnCheckedChangeListener { _, _ -> chips[checkedChipId]?.let(onChange) }
    }

    fun getCheckedChip(): Chip = findViewById(checkedChipId)

    private fun createChipView(id: Int, text: String): Chip {
        val chip = LayoutEnumToolChipBinding.inflate(context.inflater)
        chip.root.id = id
        chip.root.text = text
        chip.root.setOnClickListener { check(id) }
        return chip.root
    }
}
