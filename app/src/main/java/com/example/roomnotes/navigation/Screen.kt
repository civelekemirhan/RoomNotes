package com.example.roomnotes.navigation

import com.example.roomnotes.AppConstants

sealed class Screen(val route:String) {

    object Main:Screen(AppConstants.MAIN_SCREEN_ROUTE_KEY)
    object AddNote:Screen(AppConstants.ADD_NOTE_SCREEN_ROUTE_KEY)

}