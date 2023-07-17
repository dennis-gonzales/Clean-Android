package com.dnnsgnzls.core.repository

import com.dnnsgnzls.core.data.Note
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class NoteRepository(
    private val dataSource: INoteDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun insert(note: Note): Boolean {
        dataSource.insert(note)
        return true
    }

    suspend fun get(noteId: Long): Note? {
        return dataSource.get(noteId)
    }

    suspend fun getAll(): List<Note> {
        return withContext(dispatcher) {
            // Here, we're simulating a network operation
            delay(2000)  // Simulate network delay
            dataSource.getAll()
        }
    }

    suspend fun delete(noteId: Long): Boolean {
        dataSource.delete(noteId)
        return true
    }
}