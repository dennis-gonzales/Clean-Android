package com.dnnsgnzls.modern.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.dnnsgnzls.core.data.Note

class NoteDiffCallback(
    private val oldList: List<Note>,
    private val newList: List<Note>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        // Checks if two items represent the same entity or object, typically by comparing their unique IDs.
        val oldNote = oldList[oldItemPosition]
        val newNote = newList[newItemPosition]
        return oldNote.id == newNote.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        // Checks if the data of two items is the same, i.e., whether the contents of an item have changed.
        val oldNote = oldList[oldItemPosition]
        val newNote = newList[newItemPosition]
        return oldNote == newNote
    }
}