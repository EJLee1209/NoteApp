package com.dldmswo1209.noteapp.data.repository

import com.dldmswo1209.noteapp.data.model.Note

interface NoteRepository{

    fun getNotes() : List<Note>
}