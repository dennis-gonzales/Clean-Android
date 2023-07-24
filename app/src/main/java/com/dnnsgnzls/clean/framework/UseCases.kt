package com.dnnsgnzls.clean.framework

import com.dnnsgnzls.core.usecase.DeleteNote
import com.dnnsgnzls.core.usecase.GetAllNotes
import com.dnnsgnzls.core.usecase.GetNote
import com.dnnsgnzls.core.usecase.InsertNote

data class UseCases(
    val insertNote: InsertNote,
    val getNote: GetNote,
    val getAllNotes: GetAllNotes,
    val deleteNote: DeleteNote,
)