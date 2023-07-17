package com.dnnsgnzls.core.repository

import com.dnnsgnzls.core.data.Note

interface INoteDataSource {
    suspend fun insert(note: Note): Boolean

    suspend fun get(noteId: Long): Note?

    suspend fun getAll(): List<Note>

    suspend fun delete(note: Note): Boolean
}