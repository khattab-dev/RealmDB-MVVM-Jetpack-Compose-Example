package com.example.domain

import com.example.domain.models.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getNotes() : Flow<List<Note>>
    suspend fun insertNote(note: Note)
    suspend fun deleteNote(note: Note)
    suspend fun updateNote(note: Note)

    suspend fun clearAllNotes()
}