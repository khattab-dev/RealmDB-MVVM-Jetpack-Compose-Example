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
import kotlin.random.Random

class NoteRepoImpl @Inject constructor(private val realm: Realm) : NoteRepository {
    override fun getNotes(): Flow<List<Note>> {
        return realm.query<NoteEntity>().find().asFlow().map {
            it.list.toNotes()
        }
    }

    override suspend fun insertNote(note: Note) {
        realm.write {
            copyToRealm(
                NoteEntity(
                    title = note.title
                )
            )
        }
    }

    override suspend fun deleteNote(note: Note) {
        realm.write {
            val noteEntity = realm.query<NoteEntity>("_id == ${note.id}",note.id).find().first()

            findLatest(noteEntity).also {
                it?.let { delete(it) }
            }
        }
    }

    override suspend fun updateNote(note: Note) {
        realm.write {
            val noteEntity = realm.query<NoteEntity>("_id == ${note.id}",note.id).find().first()

            findLatest(noteEntity).also {
                it?.let { it.title = Random.nextInt(1000).toString() }
            }
        }
    }

    override suspend fun clearAllNotes() {
        realm.write {
            val notes = query<NoteEntity>().find()

            delete(notes)
        }
    }
}