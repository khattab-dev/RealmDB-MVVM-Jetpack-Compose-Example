package com.example.realmdbdemo.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.NoteRepository
import com.example.domain.models.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class HomeViewModel @Inject constructor(private val noteRepository: NoteRepository) : ViewModel() {

    val notes = noteRepository.getNotes()

    fun insertNote() = viewModelScope.launch(Dispatchers.IO) {
        noteRepository.insertNote(
            Note(
                id = System.currentTimeMillis(),
                title = Random.nextInt(1000).toString()
            )
        )
    }

    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        noteRepository.deleteNote(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        noteRepository.updateNote(note)
    }

    fun clearAllNotes() = viewModelScope.launch(Dispatchers.IO) {
        noteRepository.clearAllNotes()
    }
}