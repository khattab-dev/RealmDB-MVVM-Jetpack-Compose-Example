package com.example.domain

import com.example.domain.models.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getNotes() : Flow<List<Note>>
    fun insertNote(note: Note)
    fun deleteNote(note: Note)
}