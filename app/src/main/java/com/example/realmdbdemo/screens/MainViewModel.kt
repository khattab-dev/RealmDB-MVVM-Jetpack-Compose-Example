package com.example.realmdbdemo.screens

import androidx.lifecycle.ViewModel
import com.example.domain.NoteRepository
import com.example.domain.models.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {
    val notes = noteRepository.getNotes()

    fun insertNote(note: Note) {
        noteRepository.insertNote(note)
    }

    fun deleteNote(note: Note) {
        noteRepository.deleteNote(note)
    }
}