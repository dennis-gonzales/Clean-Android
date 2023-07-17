package com.dnnsgnzls.core.usecase

import com.dnnsgnzls.core.repository.NoteRepository

class DeleteNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(noteId: Long) = noteRepository.delete(noteId)
}