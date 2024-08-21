package com.example.roomnotes.data

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb


data class NoteState (

    val notes: List<Note> = emptyList(),
    val noteTitle: String = "",
    val noteContent: String = "",
    val noteColor: Int =0,
    val filterType: FilterType = FilterType.CLR_ALL

)