package com.dnnsgnzls.core.usecase

import com.dnnsgnzls.core.repository.NoteRepository

class GetAllNotes(private val noteRepository: NoteRepository) {
    suspend operator fun invoke() = noteRepository.getAll()
}