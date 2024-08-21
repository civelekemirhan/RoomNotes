package com.example.roomnotes.data

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NoteViewModel(
    private val dao: NoteDao
) : ViewModel() {


    val _filterType = MutableStateFlow(FilterType.CLR_ALL)
    val _notes = _filterType
        .flatMapLatest { _filterType ->
            when (_filterType.ordinal) {
                0 -> dao.getAllNotes()
                1 -> dao.getNotesByColorDefault()
                2 -> dao.getNotesByColorWarning()
                3 -> dao.getNotesByColorImportant()
                else -> {
                    dao.getAllNotes()
                }
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val _state = MutableStateFlow(NoteState())
    val state = combine(_state, _filterType, _notes) { state, filterType, notes ->
        state.copy(
            notes = notes,
            filterType = filterType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), NoteState())


    fun onEvent(event: NoteEvent) {
        when (event) {

            NoteEvent.SaveNote -> {
                val noteTitle = state.value.noteTitle
                val noteContent = state.value.noteContent
                val noteColor = state.value.noteColor
                if (noteTitle.isBlank() || noteContent.isBlank()) {
                    return
                }
                val note =
                    Note(noteTitle = noteTitle, noteContent = noteContent, noteColor = noteColor)
                viewModelScope.launch {
                    dao.addNote(note)
                }
                _state.update {
                    it.copy(
                        noteTitle = "",
                        noteContent = "",
                        noteColor = 0
                    )
                }

            }

            is NoteEvent.DeleteNote -> {
                viewModelScope.launch {
                    dao.deleteNote(event.note)
                }
            }

            is NoteEvent.SetNoteColor -> {
                _state.update {
                    it.copy(
                        noteColor = event.color
                    )
                }
            }

            is NoteEvent.SetNoteTitle -> {
                _state.update {
                    it.copy(
                        noteTitle = event.title
                    )
                }
            }

            is NoteEvent.SetNoteContent -> {
                _state.update {
                    it.copy(
                        noteContent = event.content
                    )
                }
            }

            is NoteEvent.FilterNotes -> {
                _filterType.value = event.filterType
            }


        }
    }

}