package com.dnnsgnzls.modern.framework.di

import android.app.Application
import com.dnnsgnzls.core.repository.NoteRepository
import com.dnnsgnzls.modern.framework.RoomNoteDataSource
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideRepository(app: Application) = NoteRepository(RoomNoteDataSource(app))
}