package com.dnnsgnzls.modern.framework.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.dnnsgnzls.core.data.Note
import com.dnnsgnzls.modern.framework.UseCases
import com.dnnsgnzls.modern.framework.di.ApplicationModule
import com.dnnsgnzls.modern.framework.di.DaggerViewModelComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class ListViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    @Inject
    lateinit var useCases: UseCases

    init {
        DaggerViewModelComponent.builder()
            .applicationModule(ApplicationModule((getApplication())))
            .build()
            .inject(this)
    }

    private val _noteList = MutableStateFlow<List<Note>>(emptyList())

    val noteList: StateFlow<List<Note>>
        get() = _noteList

    init {
        viewModelScope.launch {
            useCases.getAllNotes().collect { noteList ->
                _noteList.value = noteList
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}