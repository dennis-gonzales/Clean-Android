package com.dnnsgnzls.clean.framework.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dnnsgnzls.clean.framework.db.entity.NoteEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntity(noteEntity: NoteEntity)

    @Query("SELECT * FROM note WHERE id=:noteId")
    fun getEntity(noteId: Long): Flow<NoteEntity?>

    @Query("SELECT * FROM note ORDER BY update_time DESC")
    fun getAllEntities(): Flow<List<NoteEntity>>

    @Delete
    suspend fun deleteEntity(noteEntity: NoteEntity)
}