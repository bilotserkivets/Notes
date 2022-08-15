package com.example.notes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notes.*

@Composable
fun NoteNavHost(viewModel: MainViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NoteRoute.StartScreen.route) {
        composable(NoteRoute.StartScreen.route) {
            StartScreen(navController = navController, viewModel = viewModel)
        }
        composable(NoteRoute.MainScreen.route) {
            MainScreen(navController = navController, viewModel = viewModel)
        }
        composable(NoteRoute.NoteScreen.route) {
            NoteScreen(navController = navController, viewModel = viewModel)
        }
        composable(NoteRoute.AddScreen.route) {
            AddScreen(navController = navController, viewModel = viewModel)
        }
    }
}