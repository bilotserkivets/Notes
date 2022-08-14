package com.example.notes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notes.AddScreen
import com.example.notes.MainScreen
import com.example.notes.NoteScreen
import com.example.notes.StartScreen

@Composable
fun NoteNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NoteRoute.StartScreen.route) {
        composable(NoteRoute.StartScreen.route) {
            StartScreen(navController = navController)
        }
        composable(NoteRoute.MainScreen.route) {
            MainScreen(navController = navController)
        }
        composable(NoteRoute.NoteScreen.route) {
            NoteScreen(navController = navController)
        }
        composable(NoteRoute.AddScreen.route) {
            AddScreen(navController = navController)
        }
    }
}