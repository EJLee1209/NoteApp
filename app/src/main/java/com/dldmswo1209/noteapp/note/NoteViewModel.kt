package com.dldmswo1209.noteapp.note

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dldmswo1209.noteapp.data.model.Note
import com.dldmswo1209.noteapp.data.repository.NoteRepository
import com.dldmswo1209.noteapp.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val repository: NoteRepository
): ViewModel() {

    private var _notes = MutableLiveData<UiState<List<Note>>>()
    val notes: LiveData<UiState<List<Note>>> = _notes

    private var _addNote = MutableLiveData< UiState<Pair<Note,String>>>()
    val addNote: LiveData<UiState<Pair<Note,String>>> = _addNote

    private var _updateNote = MutableLiveData< UiState<String>>()
    val updateNote: LiveData<UiState<String>> = _updateNote

    private var _deleteNote = MutableLiveData< UiState<String>>()
    val deleteNote: LiveData<UiState<String>> = _deleteNote

    fun getNotes() {
        _notes.value = UiState.Loading
        repository.getNotes { _notes.postValue(it) }
    }

    fun addNote(note: Note){
        _addNote.value = UiState.Loading
        repository.addNote(note){ _addNote.postValue(it) }
    }

    fun updateNote(note: Note){
        _updateNote.value = UiState.Loading
        repository.updateNote(note){ _updateNote.postValue(it) }
    }

    fun deleteNote(note: Note) {
        _deleteNote.value = UiState.Loading
        repository.deleteNote(note){ _deleteNote.postValue(it) }
    }


}