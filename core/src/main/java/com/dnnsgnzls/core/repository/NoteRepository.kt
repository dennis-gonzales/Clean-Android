package com.dnnsgnzls.core.repository

import com.dnnsgnzls.core.data.Note

class NoteRepository(
    private val dataSource: INoteDataSource
) {
    suspend fun insert(note: Note): Boolean {
        dataSource.insert(note)
        return true
    }

    suspend fun get(noteId: Long): Note? {
        return dataSource.get(noteId)
    }

    suspend fun getAll(): List<Note> {
        return dataSource.getAll()
    }

    suspend fun delete(note: Note): Boolean {
        dataSource.delete(note)
        return true
    }
}