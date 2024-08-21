package com.example.roomnotes.data

import androidx.compose.ui.graphics.Color

sealed interface NoteEvent {

    class SetNoteTitle(val title:String):NoteEvent
    class SetNoteContent(val content:String):NoteEvent
    class SetNoteColor(val color: Int):NoteEvent
    object SaveNote:NoteEvent
    class DeleteNote(val note:Note):NoteEvent
    class FilterNotes(val filterType: FilterType):NoteEvent

}