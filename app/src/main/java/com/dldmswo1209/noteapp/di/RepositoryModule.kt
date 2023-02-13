package com.dldmswo1209.noteapp.di

import com.dldmswo1209.noteapp.data.repository.NoteRepository
import com.dldmswo1209.noteapp.data.repository.NoteRepositoryImpl
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideNoteRepository(
        database: FirebaseFirestore
    ) : NoteRepository = NoteRepositoryImpl(database)

}