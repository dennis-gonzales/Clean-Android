package com.dnnsgnzls.clean.framework.di

import com.dnnsgnzls.core.repository.NoteRepository
import com.dnnsgnzls.core.usecase.DeleteNote
import com.dnnsgnzls.core.usecase.GetAllNotes
import com.dnnsgnzls.core.usecase.GetNote
import com.dnnsgnzls.core.usecase.InsertNote
import com.dnnsgnzls.clean.framework.UseCases
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {
    @Provides
    fun getUseCases(repository: NoteRepository) = UseCases(
        InsertNote(repository),
        GetNote(repository),
        GetAllNotes(repository),
        DeleteNote(repository)
    )
}