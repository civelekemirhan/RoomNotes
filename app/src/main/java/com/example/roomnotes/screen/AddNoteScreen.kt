package com.example.roomnotes.screen

import android.widget.Toast
import androidx.compose.animation.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults

import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon

import androidx.compose.material3.Scaffold

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.roomnotes.AppConstants
import com.example.roomnotes.appcomponents.AppTextField
import com.example.roomnotes.data.NoteEvent
import com.example.roomnotes.data.NoteState


@Composable
fun AddNoteScreen(navController: NavController, onEvent: (NoteEvent) -> Unit, state: NoteState) {

    val hashList = hashMapOf<Int, Color>()
    hashList[0] = Color.White
    hashList[1] = Color.Yellow
    hashList[2] = Color.Red

    val context = LocalContext.current


    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(NoteEvent.SaveNote)
                Toast.makeText(context, "Note Saved", Toast.LENGTH_SHORT).show()
                navController.navigate(AppConstants.MAIN_SCREEN_ROUTE_KEY) {
                    popUpTo(AppConstants.MAIN_SCREEN_ROUTE_KEY) {
                        inclusive = true
                    }
                }
            }, containerColor = Color.Black) {
                Icon(imageVector = Icons.Default.Done, contentDescription = "", tint = Color.White)
            }
        }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(hashList[state.noteColor] ?: Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(top = 60.dp, bottom = 60.dp),
                horizontalArrangement = Arrangement.SpaceBetween,

                ) {

                hashList.keys.forEach() {
                    Button(
                        modifier = Modifier
                            .size(50.dp), onClick = {
                            onEvent(NoteEvent.SetNoteColor(it))
                        },
                        shape = CircleShape,
                        contentPadding = PaddingValues(), colors = ButtonDefaults.buttonColors(
                            backgroundColor = hashList.getValue(it),
                        )
                    ) {
                        Box(
                            modifier =
                            Modifier
                                .fillMaxSize()
                                .background(hashList.getValue(it), CircleShape)
                                .border(3.dp, Color.Black, CircleShape)
                        )
                    }
                }


            }

            Column(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(500.dp),
            ) {
                Row {
                    AppTextField(
                        borderColor = Color.Transparent,
                        textColor = Color.Black,
                        placeholderText = "Enter Title ...",
                        textSize = 25.sp
                    ) {
                        onEvent(NoteEvent.SetNoteTitle(it))
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
                Row {
                    AppTextField(
                        borderColor = Color.Transparent,
                        textColor = Color.Black,
                        maxLines = 16,
                        placeholderText = "Enter Text ...",
                        textSize = 16.sp
                    ) {
                        onEvent(NoteEvent.SetNoteContent(it))
                    }
                }


            }


        }
    }


}