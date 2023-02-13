package com.dldmswo1209.noteapp.data.repository

import com.dldmswo1209.noteapp.data.model.Note
import com.dldmswo1209.noteapp.util.UiState

interface NoteRepository{
    fun getNotes() : UiState<List<Note>>
}