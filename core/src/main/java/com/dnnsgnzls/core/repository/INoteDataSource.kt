package com.dnnsgnzls.core.repository

import com.dnnsgnzls.core.data.Note
import kotlinx.coroutines.flow.Flow

interface INoteDataSource {
    suspend fun insert(note: Note): Boolean

    fun get(noteId: Long): Flow<Note?>

    fun getAll(): Flow<List<Note>>

    suspend fun delete(note: Note): Boolean
}