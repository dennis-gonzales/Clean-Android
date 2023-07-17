package com.dnnsgnzls.core.data

data class Note(
    val title: String,
    val content: String,
    val creationTime: Long,
    val updateTime: Long,
    val id: Long = 0
)