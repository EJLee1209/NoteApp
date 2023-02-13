package com.dldmswo1209.noteapp.note

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dldmswo1209.noteapp.data.model.Note
import com.dldmswo1209.noteapp.data.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val repository: NoteRepository
): ViewModel() {

    private var _notes = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>> = _notes

    fun getNotes() {
        _notes.postValue(repository.getNotes())
    }


}