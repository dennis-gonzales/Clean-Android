package com.dnnsgnzls.modern.presentation.adapter

import android.view.View
import com.dnnsgnzls.core.data.Note

interface INoteClick {
    fun onClick(view: View, note: Note): Unit
}