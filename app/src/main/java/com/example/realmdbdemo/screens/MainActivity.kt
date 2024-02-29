package com.example.realmdbdemo.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.domain.models.Note
import com.example.realmdbdemo.ui.theme.RealmDBDemoTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val vm: MainViewModel = viewModel()
            val notes = vm.notes.collectAsState(initial = null)
            RealmDBDemoTheme {
                Scaffold(
                    floatingActionButton = {
                        FilledIconButton(onClick = {
                            vm.insertNote(
                                Note(
                                    id = System.currentTimeMillis(),
                                    title = Random.nextInt(1000).toString()
                                )
                            )
                        }) {
                            Icon(imageVector = Icons.Filled.Add, contentDescription = null)
                        }
                    },
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .padding(it)
                            .padding(16.dp)
                    ) {
                        notes.value?.let { notes ->
                            items(notes) { note ->
                                Text(text = note.title, modifier = Modifier.clickable {
                                    vm.deleteNote(note)
                                })
                            }
                        }
                    }
                }
            }
        }
    }
}
