package com.example.data.entity

import com.example.domain.models.Note
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

open class NoteEntity(
    @PrimaryKey
    private var _id: Long = System.currentTimeMillis(),
    var title: String
) : RealmObject {

    // Empty constructor for realm
    constructor() : this(_id = System.currentTimeMillis(), title = "")

    companion object {
        fun NoteEntity.toNote() : Note {
            return Note(
                id = _id,
                title = this.title
            )
        }

        fun List<NoteEntity>.toNotes() : List<Note> {
            return this.map {
                Note(
                    id = it._id,
                    title = it.title
                )
            }
        }
    }
}
