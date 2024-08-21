package com.example.roomnotes.data

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "notes_table")
data class Note(
@PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val noteTitle:String,
    val noteContent:String,
    val noteColor: Int,
)
