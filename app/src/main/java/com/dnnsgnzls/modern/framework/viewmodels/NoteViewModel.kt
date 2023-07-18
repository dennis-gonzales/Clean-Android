package com.dnnsgnzls.modern.framework.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dnnsgnzls.core.data.Note
import com.dnnsgnzls.core.repository.NoteRepository
import com.dnnsgnzls.core.usecase.DeleteNote
import com.dnnsgnzls.core.usecase.GetAllNotes
import com.dnnsgnzls.core.usecase.GetNote
import com.dnnsgnzls.core.usecase.InsertNote
import com.dnnsgnzls.modern.framework.RoomNoteDataSource
import com.dnnsgnzls.modern.framework.UseCases
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = NoteRepository(RoomNoteDataSource(application))

    private val useCases = UseCases(
        InsertNote(repository),
        GetNote(repository),
        GetAllNotes(repository),
        DeleteNote(repository)
    )

    private val _saved = MutableLiveData<Boolean>()

    val saved: LiveData<Boolean>
        get() = _saved

    fun saveNote(note: Note) {
        viewModelScope.launch {
            useCases.insertNote(note)
            _saved.postValue(true)
        }
    }
}