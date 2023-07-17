package com.dnnsgnzls.modern.framework.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dnnsgnzls.modern.framework.persistence.dao.NoteDao
import com.dnnsgnzls.modern.framework.persistence.entity.NoteEntity

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

        private const val DATABASE_NAME = "hero-db";

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