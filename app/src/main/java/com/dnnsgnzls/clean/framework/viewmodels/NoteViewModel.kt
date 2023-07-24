package com.dnnsgnzls.clean.framework.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.dnnsgnzls.core.data.Note
import com.dnnsgnzls.clean.framework.UseCases
import com.dnnsgnzls.clean.framework.di.ApplicationModule
import com.dnnsgnzls.clean.framework.di.DaggerViewModelComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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

    private val _note = MutableStateFlow<Note?>(null)
    val note: StateFlow<Note?>
        get() = _note

    fun getNote(noteId: Long) {
        viewModelScope.launch {
          useCases.getNote(noteId).collect {note ->
              _note.value = note
          }
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