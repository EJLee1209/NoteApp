package com.dldmswo1209.noteapp.note

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.dldmswo1209.noteapp.R
import com.dldmswo1209.noteapp.data.model.Note
import com.dldmswo1209.noteapp.databinding.FragmentNoteListingBinding
import com.dldmswo1209.noteapp.util.UiState
import com.dldmswo1209.noteapp.util.hide
import com.dldmswo1209.noteapp.util.show
import com.dldmswo1209.noteapp.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteListingFragment : Fragment() {
    private lateinit var binding: FragmentNoteListingBinding
    private val viewModel: NoteViewModel by viewModels()

    private val adapter by lazy{
        NoteListingAdapter(
            onItemClicked = { pos, item->
                findNavController().navigate(R.id.action_noteListingFragment_to_noteDetailFragment, Bundle().apply {
                    putParcelable("note", item)
                })
            }
        )
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteListingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerview.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.recyclerview.adapter = adapter
        binding.btnCreate.setOnClickListener {
            findNavController().navigate(R.id.action_noteListingFragment_to_noteDetailFragment)
        }

        viewModel.getNotes()
        viewModel.notes.observe(viewLifecycleOwner){state->
            when(state){
                is UiState.Loading -> {
                    binding.progressBar.show()
                }
                is UiState.Failure -> {
                    binding.progressBar.hide()
                    toast(state.error)
                }
                is UiState.Success -> {
                    binding.progressBar.hide()
                    adapter.submitList(state.data.toMutableList())
                }
            }
        }

//        viewModel.deleteNote.observe(viewLifecycleOwner){state->
//            when(state){
//                is UiState.Loading -> {
//                    binding.progressBar.show()
//                }
//                is UiState.Failure -> {
//                    binding.progressBar.hide()
//                    toast(state.error)
//                }
//                is UiState.Success -> {
//                    binding.progressBar.hide()
//                    toast(state.data)
//                    if(deletePosition != -1){
//                        adapter.removeItem(deletePosition)
//                    }
//                }
//            }
//        }
    }
}