package com.dldmswo1209.noteapp.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.dldmswo1209.noteapp.data.model.Note
import com.dldmswo1209.noteapp.databinding.FragmentNoteDetailBinding
import com.dldmswo1209.noteapp.util.UiState
import com.dldmswo1209.noteapp.util.hide
import com.dldmswo1209.noteapp.util.show
import com.dldmswo1209.noteapp.util.toast
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class NoteDetailFragment : Fragment() {
    private lateinit var binding: FragmentNoteDetailBinding
    private val viewModel: NoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCreate.setOnClickListener {
            if(validataion()){
                viewModel.addNote(
                    Note(
                        id = "",
                        text = binding.noteMsg.text.toString(),
                        date = Date()
                    )
                )
            }
        }

        viewModel.addNote.observe(viewLifecycleOwner){state->
            when(state){
                is UiState.Loading -> {
                    binding.progressBar.show()
                    binding.btnCreate.text = ""
                }
                is UiState.Failure -> {
                    binding.progressBar.hide()
                    binding.btnCreate.text = "Create"
                    toast(state.error)
                }
                is UiState.Success -> {
                    binding.progressBar.hide()
                    binding.btnCreate.text = "Create"
                    toast(state.data)
                }
            }
        }
    }

    private fun validataion(): Boolean {
        var isValid = true
        if(binding.noteMsg.text.toString().isEmpty()){
            isValid = false
            toast("Enter message")
        }

        return isValid
    }
}