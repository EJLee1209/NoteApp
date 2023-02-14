package com.dldmswo1209.noteapp.ui.note

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dldmswo1209.noteapp.data.model.Note
import com.dldmswo1209.noteapp.databinding.ItemNoteLayoutBinding
import com.dldmswo1209.noteapp.util.addChip
import com.dldmswo1209.noteapp.util.hide
import java.text.SimpleDateFormat

class NoteListingAdapter(
    val onItemClicked: (Int, Note) -> Unit,
) : ListAdapter<Note, NoteListingAdapter.MyViewHolder>(diffUtil) {

    val sdf = SimpleDateFormat("dd MMM yyyy")

    inner class MyViewHolder(val binding: ItemNoteLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(note: Note){
            binding.title.text = note.title
            binding.date.text = sdf.format(note.date)
            binding.tags.apply {
                if(note.tags.isNullOrEmpty()){
                    hide()
                }else{
                    removeAllViews()
                    if(note.tags.size > 2){
                        note.tags.subList(0,2).forEach{ tag-> addChip(tag) }
                        addChip("+${note.tags.size - 2}")
                    }else{
                        note.tags.forEach{ tag-> addChip(tag) }
                    }
                }
            }
            binding.desc.apply {
                if(note.description.length > 120){
                    text = "${note.description.substring(0, 120)}..."
                }else{
                    text = note.description
                }
            }

            binding.itemLayout.setOnClickListener { onItemClicked(bindingAdapterPosition, note) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemNoteLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    fun removeItem(position: Int){
        val currentList = currentList.toMutableList()
        if(currentList.size <= position) return
        currentList.removeAt(position)
        submitList(currentList)
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