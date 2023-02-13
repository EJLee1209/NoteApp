package com.dldmswo1209.noteapp.data.repository

import com.dldmswo1209.noteapp.data.model.Note
import com.dldmswo1209.noteapp.util.FireStoreTables
import com.dldmswo1209.noteapp.util.UiState
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Date

class NoteRepositoryImpl(
    val database: FirebaseFirestore
) : NoteRepository {
    override fun getNotes(result: (UiState<List<Note>>) -> Unit)  {
        database.collection(FireStoreTables.NOTE)
            .get()
            .addOnSuccessListener {
                val notes = arrayListOf<Note>()
                for(document in it){
                    val note = document.toObject(Note::class.java)
                    notes.add(note)
                }
                result.invoke(
                    UiState.Success(notes)
                )
            }
            .addOnFailureListener {
                result.invoke(
                    UiState.Failure(
                        it.localizedMessage
                    )
                )
            }
    }

    override fun addNote(note: Note, result: (UiState<String>) -> Unit) {
        val document = database.collection(FireStoreTables.NOTE).document()
        note.id = document.id

        document
            .set(note)
            .addOnSuccessListener {
                result.invoke(
                    UiState.Success("Note has been created successfully")
                )
            }
            .addOnFailureListener {
                result.invoke(
                    UiState.Failure(
                        it.localizedMessage
                    )
                )
            }
    }
}