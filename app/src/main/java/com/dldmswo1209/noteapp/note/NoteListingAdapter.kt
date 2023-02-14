package com.dldmswo1209.noteapp.note

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dldmswo1209.noteapp.data.model.Note
import com.dldmswo1209.noteapp.databinding.ItemNoteLayoutBinding

class NoteListingAdapter(
    val onItemClicked: (Int, Note) -> Unit,
    val onEditClicked: (Int, Note) -> Unit,
    val onDeleteClicked: (Int, Note) -> Unit
) : ListAdapter<Note, NoteListingAdapter.MyViewHolder>(diffUtil) {

    inner class MyViewHolder(private val binding: ItemNoteLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(note: Note){
            binding.tvNoteIdValue.text = note.id
            binding.tvMsgValue.text = note.text
            binding.btnModify.setOnClickListener { onEditClicked(adapterPosition, note) }
            binding.btnDelete.setOnClickListener { onDeleteClicked(adapterPosition, note) }
            binding.itemLayout.setOnClickListener { onItemClicked(adapterPosition, note) }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemNoteLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    fun removeItem(position: Int){
        currentList.removeAt(position)
    }

    companion object{
        val diffUtil = object: DiffUtil.ItemCallback<Note>(){
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem == newItem
            }

        }
    }
}