package com.maximbircu.devtools.android.presentation.tools.enum.selectors.dialog

interface EnumToolOptionsAdapterDelegate {
    fun getItemCount(): Int
    fun addItems(options: List<String>)
    fun getItem(index: Int): String
    fun getSelectedItem(): String
    fun getSelectedItemPosition(): Int
    fun selectItem(index: Int)
    fun selectItem(optionToSelect: String)
    fun isItemSelected(position: Int): Boolean

    companion object {
        fun create(): EnumToolOptionsAdapterDelegate = EnumToolOptionsAdapterDelegateImpl()
    }
}

private class EnumToolOptionsAdapterDelegateImpl : EnumToolOptionsAdapterDelegate {
    private val items: MutableList<String> = mutableListOf()
    private var selectedItemPosition: Int = -1

    override fun getItemCount() = items.count()

    override fun isItemSelected(position: Int) = position == selectedItemPosition

    override fun getItem(index: Int) = items[index]

    override fun getSelectedItem() = items[selectedItemPosition]

    override fun getSelectedItemPosition() = selectedItemPosition

    override fun addItems(options: List<String>) {
        this.items += options
    }

    override fun selectItem(optionToSelect: String) = selectItem(items.indexOf(optionToSelect))

    override fun selectItem(index: Int) {
        selectedItemPosition = index
    }
}
