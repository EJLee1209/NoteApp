package com.dldmswo1209.noteapp.data.repository

import com.dldmswo1209.noteapp.data.model.Note
import com.dldmswo1209.noteapp.util.UiState
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Date

class NoteRepositoryImpl(
    val database: FirebaseFirestore
) : NoteRepository {
    override fun getNotes(): UiState<List<Note>> {
        val data = listOf<Note>()

        return if(data.isEmpty()){
            UiState.Failure("Data is Empty")
        }else{
            UiState.Success(data)
        }
    }
}