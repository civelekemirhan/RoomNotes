package com.example.roomnotes.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDao {

    @Insert
    suspend fun addNote(note:Note)

    @Delete
    suspend fun deleteNote(note:Note)

    @Query("SELECT * FROM notes_table ORDER BY id ASC")
    fun getAllNotes(): Flow<List<Note>>

    @Query("SELECT * FROM notes_table WHERE noteColor = 0 ORDER BY id ASC")
    fun getNotesByColorDefault():Flow<List<Note>>

    @Query("SELECT * FROM notes_table WHERE noteColor = 1 ORDER BY id ASC")
    fun getNotesByColorWarning():Flow<List<Note>>

    @Query("SELECT * FROM notes_table WHERE noteColor = 2 ORDER BY id ASC")
    fun getNotesByColorImportant():Flow<List<Note>>


}