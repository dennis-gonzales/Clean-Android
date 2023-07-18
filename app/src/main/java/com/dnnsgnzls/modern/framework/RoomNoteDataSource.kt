package com.dnnsgnzls.modern.framework

import android.content.Context
import com.dnnsgnzls.core.data.Note
import com.dnnsgnzls.core.repository.INoteDataSource
import com.dnnsgnzls.modern.framework.db.DatabaseService
import com.dnnsgnzls.modern.framework.db.dao.NoteDao
import com.dnnsgnzls.modern.framework.db.entity.NoteEntity

class RoomNoteDataSource(context: Context) : INoteDataSource {

    private val noteDao: NoteDao = DatabaseService(context).noteDao()

    override suspend fun insert(note: Note): Boolean {
        noteDao.insertEntity(NoteEntity.fromNote(note))
        return true
    }

    override suspend fun get(noteId: Long): Note? {
        return noteDao.getEntity(noteId)?.toNote()
    }

    override suspend fun getAll(): List<Note> {
        return noteDao.getAllEntities().map { it.toNote() }
    }

    override suspend fun delete(note: Note): Boolean {
        noteDao.deleteEntity(NoteEntity.fromNote(note))
        return true
    }
}