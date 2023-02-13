package com.dldmswo1209.noteapp.data.repository

import com.dldmswo1209.noteapp.data.model.Note

class NoteRepositoryImpl : NoteRepository {
    override fun getNotes(): List<Note> {
        return arrayListOf()
    }
}