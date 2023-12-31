package com.dnnsgnzls.clean.framework.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dnnsgnzls.core.data.Note

@Entity(tableName = "note")
data class NoteEntity(
    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "content")
    val content: String,

    @ColumnInfo(name = "creation_date")
    val creationDate: Long,

    @ColumnInfo(name = "update_time")
    val updateTime: Long,

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0

) {
    companion object {
        fun fromNote(note: Note): NoteEntity = NoteEntity(
            title = note.title,
            content = note.content,
            creationDate = note.creationDate,
            updateTime = note.updateTime,
            id = note.id
        )
    }

    fun toNote(): Note = Note(
        title = title,
        content = content,
        creationDate = creationDate,
        updateTime = updateTime,
        id = id
    )
}