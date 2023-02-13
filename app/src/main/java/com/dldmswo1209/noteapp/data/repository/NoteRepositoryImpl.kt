package com.dldmswo1209.noteapp.data.repository

import com.dldmswo1209.noteapp.data.model.Note
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Date

class NoteRepositoryImpl(
    val database: FirebaseFirestore
) : NoteRepository {
    override fun getNotes(): List<Note> {
        return arrayListOf(
            Note(
                "테스트",
                "Note1",
                Date()
            ),
            Note(
                "테스트",
                "Note2",
                Date()
            ),
            Note(
                "테스트",
                "Note3",
                Date()
            ),
        )
    }
}