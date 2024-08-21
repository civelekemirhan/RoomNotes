package com.example.roomnotes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.roomnotes.AppConstants
import com.example.roomnotes.data.NoteEvent
import com.example.roomnotes.data.NoteState
import com.example.roomnotes.screen.AddNoteScreen
import com.example.roomnotes.screen.MainScreen


@Composable
fun SetUpNavGraph(navController: NavHostController, onEvent: (NoteEvent) -> Unit, state: NoteState) {

    NavHost(
        startDestination = AppConstants.MAIN_SCREEN_ROUTE_KEY,
        navController = navController
    ) {

        composable(route = AppConstants.MAIN_SCREEN_ROUTE_KEY) {
            MainScreen(navController = navController,onEvent,state)
        }
        composable(route =AppConstants.ADD_NOTE_SCREEN_ROUTE_KEY) {
            AddNoteScreen(navController = navController,onEvent,state)
        }

    }

}