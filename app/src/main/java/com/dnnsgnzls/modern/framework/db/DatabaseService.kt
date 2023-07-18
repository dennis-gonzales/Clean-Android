package com.dnnsgnzls.modern.framework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dnnsgnzls.modern.framework.db.dao.NoteDao
import com.dnnsgnzls.modern.framework.db.entity.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class DatabaseService : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var instance: DatabaseService? = null

        private const val DATABASE_NAME = "note-db";

        operator fun invoke(context: Context): DatabaseService = instance ?: synchronized(this) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            DatabaseService::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }
}