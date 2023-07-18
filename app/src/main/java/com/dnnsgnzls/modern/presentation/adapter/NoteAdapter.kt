package com.dnnsgnzls.modern.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dnnsgnzls.core.data.Note
import com.dnnsgnzls.modern.databinding.NoteItemBinding
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class NoteAdapter(
    private val noteList: ArrayList<Note>,
    private val clickListener: INoteClick,
    private val scope: CoroutineScope
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder(
        private val binding: NoteItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(note: Note, clickListener: INoteClick) {
            binding.itemTitle.text = note.title
            binding.itemInfo.text = note.content

            binding.root.setOnClickListener {
                clickListener.onClick(binding.root, note)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NoteItemBinding.inflate(inflater, parent, false)

        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(noteList[position], clickListener)
    }

    override fun getItemCount() = noteList.size

    fun updateList(newNoteList: List<Note>, dispatcher: CoroutineDispatcher = Dispatchers.Default) {
        scope.launch {
            ensureActive() // Check for cancellation

            val diffResult = withContext(dispatcher) {
                DiffUtil.calculateDiff(NoteDiffCallback(noteList, newNoteList))
            }

            withContext(Dispatchers.Main) {
                noteList.clear()
                noteList.addAll(newNoteList)
                diffResult.dispatchUpdatesTo(this@NoteAdapter)
            }
        }
    }
}