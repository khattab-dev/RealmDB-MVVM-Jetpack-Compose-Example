package com.example.realmdbdemo.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.domain.models.Note
import kotlin.random.Random

@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    vm: HomeViewModel = viewModel()
) {
    val notes by vm.notes.collectAsState(initial = null)

    Column(verticalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.padding(16.dp)) {
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(notes ?: emptyList()) {
                NoteCard(note = it,
                    onDelete = { vm.deleteNote(it) },
                    onUpdate = { vm.updateNote(it) }
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                onClick = {
                    vm.insertNote()
                }
            ) {
                Text("ADD")
            }

            Button(modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
                onClick = { vm.clearAllNotes() }
            ) {
                Text("CLEAR")
            }
        }
    }
}

@Composable
private fun NoteCard(
    note: Note,
    onDelete: () -> Unit,
    onUpdate: () -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = note.title)

            Row {
                FilledIconButton(onClick = { onUpdate() }) {
                    Icon(imageVector = Icons.Outlined.Refresh, contentDescription = null)
                }

                FilledIconButton(onClick = { onDelete() }) {
                    Icon(imageVector = Icons.Outlined.Delete, contentDescription = null)
                }
            }
        }
    }
}