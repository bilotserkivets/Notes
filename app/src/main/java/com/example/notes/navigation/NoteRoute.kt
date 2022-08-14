package com.example.notes.navigation

import com.example.notes.utils.Constants

sealed  class NoteRoute(val route: String) {
    object StartScreen : NoteRoute(Constants.Screens.START_SCREEN)
    object MainScreen : NoteRoute(Constants.Screens.MAIN_SCREEN)
    object NoteScreen : NoteRoute(Constants.Screens.NOTE_SCREEN)
    object AddScreen : NoteRoute(Constants.Screens.ADD_SCREEN)
}