package com.example.roomnotes.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.roomnotes.appcomponents.AppScaff
import com.example.roomnotes.data.FilterType
import com.example.roomnotes.data.NoteEvent
import com.example.roomnotes.data.NoteState

@Composable
fun MainScreen(navController: NavController, onEvent: (NoteEvent) -> Unit, state: NoteState) {
    val hashList = hashMapOf<Int, Color>()
    hashList[0] = Color.White
    hashList[1] = Color.Yellow
    hashList[2] = Color.Red
    AppScaff(navController = navController) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FilterType.values().forEach { filterType ->

                        Row(modifier = Modifier.clickable(
                            onClick = {
                                onEvent(NoteEvent.FilterNotes(filterType))
                            }
                        ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(selected = state.filterType == filterType, onClick = {
                                onEvent(NoteEvent.FilterNotes(filterType))
                            })
                            Text(text = filterType.name)
                        }

                    }

                }
            }
            items(state.notes) { note ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                        horizontalArrangement = Arrangement.Center,
                ) {
                    Column(
                        modifier = Modifier
                            .background(
                                hashList.getValue(note.noteColor),
                                RoundedCornerShape(10.dp)
                            )
                            .fillMaxWidth(0.8f)
                            .border(0.dp, Color.Black, RoundedCornerShape(10.dp))
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text(text = "${note.noteTitle} ", fontSize = 30.sp, fontFamily = FontFamily.Monospace)
                            Text(text = note.noteContent, fontSize = 18.sp, modifier = Modifier.padding(top=15.dp))
                        }

                    }
                    IconButton(onClick = { onEvent(NoteEvent.DeleteNote(note)) }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete Contact"
                        )
                    }

                }

            }
        }
    }

}