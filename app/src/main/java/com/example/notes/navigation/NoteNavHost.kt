package com.example.notes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notes.*
import com.example.notes.utils.Constants

@Composable
fun NoteNavHost(mViewModel: MainViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NoteRoute.StartScreen.route) {
        composable(NoteRoute.StartScreen.route) {
            StartScreen(navController = navController, viewModel = mViewModel)
        }
        composable(NoteRoute.MainScreen.route) {
            MainScreen(navController = navController, viewModel = mViewModel)
        }
        composable(NoteRoute.NoteScreen.route + "/{${Constants.Key.ID}}") { backStack ->
            NoteScreen(
                navController = navController,
                viewModel = mViewModel,
                noteId = backStack.arguments?.getString(Constants.Key.ID)
            )
        }
        composable(NoteRoute.AddScreen.route) {
            AddScreen(navController = navController, viewModel = mViewModel)
        }
    }
}