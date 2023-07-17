package com.dnnsgnzls.core.data

data class Note(
    val title: String,
    val content: String,
    val creationDate: Long,
    val updateTime: Long,
    val id: Long = 0
)