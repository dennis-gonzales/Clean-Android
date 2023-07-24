package com.dnnsgnzls.clean.framework.di

import com.dnnsgnzls.clean.framework.viewmodels.ListViewModel
import com.dnnsgnzls.clean.framework.viewmodels.NoteViewModel
import dagger.Component

@Component(modules = [ApplicationModule::class, RepositoryModule::class, UseCasesModule::class])
interface ViewModelComponent {
    fun inject(noteViewModel: NoteViewModel)
    fun inject(listViewModel: ListViewModel)
}