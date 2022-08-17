package com.example.notes

import android.app.Application
import android.support.v4.app.INotificationSideChannel
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.notes.model.Note
import com.example.notes.navigation.NoteRoute
import com.example.notes.ui.theme.Blue
import com.example.notes.ui.theme.Blue_button
import com.example.notes.utils.Constants

@Composable
fun MainScreen(navController: NavHostController, viewModel: MainViewModel){
    val notes = viewModel.getAllNotes().observeAsState(listOf()).value
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                navController.navigate(NoteRoute.AddScreen.route)
                },
                backgroundColor = Blue_button
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = Constants.Key.ADD_NOTE,
                    tint = Color.White
                )
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Blue)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
            ) {
            items(notes) { note ->
                NoteItem(navController = navController, note = note)
            }
        }
    }
}

@Composable
fun NoteItem(navController: NavHostController, note: Note) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {
                navController.navigate(NoteRoute.NoteScreen.route + "/${note.id}")
            },
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp),

        ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
            ) {
            Text(
                text = note.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = note.subtitle,
                fontSize = 14.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPrev() {
    val context = LocalContext.current
    val mViewModel: MainViewModel =
        viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
    MainScreen(navController = rememberNavController(), viewModel = mViewModel)
}