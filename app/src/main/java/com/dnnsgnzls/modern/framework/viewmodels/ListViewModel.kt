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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    private val repository = NoteRepository(RoomNoteDataSource(application))

    private val useCases = UseCases(
        InsertNote(repository),
        GetNote(repository),
        GetAllNotes(repository),
        DeleteNote(repository)
    )

    private val _noteList = MutableLiveData<List<Note>>()

    val noteList: LiveData<List<Note>>
        get() = _noteList

    fun getAllNotes() {
        viewModelScope.launch {
            _noteList.value = useCases.getAllNotes()
        }
    }
}