package com.dnnsgnzls.core.usecase

import com.dnnsgnzls.core.repository.NoteRepository

class GetNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(noteId: Long) = noteRepository.get(noteId)
}