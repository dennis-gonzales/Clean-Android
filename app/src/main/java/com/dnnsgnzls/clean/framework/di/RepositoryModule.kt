package com.dnnsgnzls.clean.framework.di

import android.app.Application
import com.dnnsgnzls.core.repository.NoteRepository
import com.dnnsgnzls.clean.framework.RoomNoteDataSource
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideRepository(app: Application) = NoteRepository(RoomNoteDataSource(app))
}