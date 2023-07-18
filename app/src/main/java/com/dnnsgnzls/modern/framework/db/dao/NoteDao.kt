package com.dnnsgnzls.modern.framework.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dnnsgnzls.modern.framework.db.entity.NoteEntity


@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntity(noteEntity: NoteEntity)

    @Query("SELECT * FROM note WHERE id=:noteId")
    suspend fun getEntity(noteId: Long): NoteEntity?

    @Query("SELECT * FROM note ORDER BY update_time DESC")
    suspend fun getAllEntities(): List<NoteEntity>

    @Delete
    suspend fun deleteEntity(noteEntity: NoteEntity)
}