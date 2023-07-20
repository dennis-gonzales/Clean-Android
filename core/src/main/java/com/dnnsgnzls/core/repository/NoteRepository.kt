package com.dnnsgnzls.core.repository

import com.dnnsgnzls.core.data.Note
import kotlinx.coroutines.flow.Flow

class NoteRepository(
    private val dataSource: INoteDataSource
) {
    suspend fun insert(note: Note): Boolean {
        dataSource.insert(note)
        return true
    }

    fun get(noteId: Long): Flow<Note?> {
        return dataSource.get(noteId)
    }

    fun getAll(): Flow<List<Note>> {
        return dataSource.getAll()
    }

    suspend fun delete(note: Note): Boolean {
        dataSource.delete(note)
        return true
    }
}