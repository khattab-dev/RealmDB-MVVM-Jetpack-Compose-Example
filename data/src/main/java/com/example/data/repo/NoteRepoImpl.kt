package com.example.data.repo

import com.example.data.entity.NoteEntity
import com.example.data.entity.NoteEntity.Companion.toNotes
import com.example.domain.NoteRepository
import com.example.domain.models.Note
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.mongodb.kbson.ObjectId
import javax.inject.Inject

class NoteRepoImpl @Inject constructor(private val realm: Realm) : NoteRepository {
    override fun getNotes(): Flow<List<Note>> {
        return realm.query<NoteEntity>().find().asFlow().map {
            it.list.toNotes()
        }
    }

    override fun insertNote(note: Note) {
        realm.writeBlocking {
            copyToRealm(
                NoteEntity(
                    title = note.title
                )
            )
        }
    }

    override fun deleteNote(note: Note) {
        realm.writeBlocking {
            findLatest(
                copyToRealm(NoteEntity(note.id, note.title),UpdatePolicy.ALL)
            ).also {
                it?.let {
                    delete(it)
                }
            }
        }
    }
}