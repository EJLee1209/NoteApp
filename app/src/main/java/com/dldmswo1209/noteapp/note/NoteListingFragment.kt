package com.dldmswo1209.noteapp.note

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.dldmswo1209.noteapp.R
import com.dldmswo1209.noteapp.databinding.FragmentNoteListingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteListingFragment : Fragment() {
    private lateinit var binding: FragmentNoteListingBinding
    private val viewModel: NoteViewModel by viewModels()
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

        viewModel.getNotes()
        viewModel.notes.observe(viewLifecycleOwner){
            Log.d("testt", "data: ${it}}")
        }
    }
}