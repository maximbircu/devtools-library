package com.maximbircu.devtools.android.presentation.tools.enumtool.selectors.dialog

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.maximbircu.devtools.android.R

internal class EnumToolOptionsAdapter(
    private var onOptionSelected: (String) -> Unit = {},
    private val delegate: EnumToolOptionsAdapterDelegate = EnumToolOptionsAdapterDelegate.create()
) : RecyclerView.Adapter<ViewHolder>() {
    fun getSelectedOptionPosition() = delegate.getSelectedItemPosition()

    fun addOptions(options: List<String>) {
        delegate.addItems(options)
        notifyDataSetChanged()
    }

    fun selectOption(option: String) = delegate.selectItem(option)

    override fun getItemCount() = delegate.getItemCount()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.layout_enum_tool_radio_button,
            parent,
            false
        )
        return object : ViewHolder(view) {}
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = delegate.getItem(position)
        val button = (holder.itemView as RadioButton)
        button.text = item
        button.isChecked = delegate.isItemSelected(position)
        button.setOnClickListener {
            delegate.selectItem(position)
            notifyDataSetChanged()
            onOptionSelected(item)
        }
    }
}
