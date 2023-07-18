package com.dnnsgnzls.modern.framework.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dnnsgnzls.core.data.Note
import com.dnnsgnzls.modern.framework.UseCases
import com.dnnsgnzls.modern.framework.di.ApplicationModule
import com.dnnsgnzls.modern.framework.di.DaggerViewModelComponent
import kotlinx.coroutines.launch
import javax.inject.Inject

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var useCases: UseCases

    init {
        DaggerViewModelComponent.builder()
            .applicationModule(ApplicationModule((getApplication())))
            .build()
            .inject(this)
    }

    private val _note = MutableLiveData<Note>()
    val note: LiveData<Note>
        get() = _note

    fun getNote(noteId: Long) {
        viewModelScope.launch {
            _note.value = useCases.getNote(noteId)
        }
    }

    fun saveNote(note: Note) {
        viewModelScope.launch {
            useCases.insertNote(note)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            useCases.deleteNote(note)
        }
    }
}