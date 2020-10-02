package dev.vladmakarenko.financialPlanning.plug.ui.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.vladmakarenko.financialPlanning.core.model.Note
import dev.vladmakarenko.financialPlanning.plug.databinding.NoteItemBinding

class NotesAdapter(diffCallback: DiffUtil.ItemCallback<Note>) :
    ListAdapter<Note, NoteViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            NoteItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position)

        holder.bind(note)
        holder.itemView.setOnClickListener{
            val _note = getItem(position)
            val direction = NotesFragmentDirections.addNote(_note)
            it.findNavController().navigate(direction)
        }
    }
}

class NoteViewHolder(val binding: NoteItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(note: Note) {
        binding.note = note
        binding.executePendingBindings()
    }
}