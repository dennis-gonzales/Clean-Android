package com.dnnsgnzls.modern.framework.di

import com.dnnsgnzls.modern.framework.viewmodels.ListViewModel
import com.dnnsgnzls.modern.framework.viewmodels.NoteViewModel
import dagger.Component

@Component(modules = [ApplicationModule::class, RepositoryModule::class, UseCasesModule::class])
interface ViewModelComponent {
    fun inject(noteViewModel: NoteViewModel)
    fun inject(listViewModel: ListViewModel)
}