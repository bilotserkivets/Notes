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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
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
import com.example.notes.navigation.NoteRoute
import com.example.notes.ui.theme.Blue
import com.example.notes.ui.theme.Blue_button
import com.example.notes.utils.Constants

@Composable
fun MainScreen(navController: NavHostController, viewModel: MainViewModel){
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Blue),
            horizontalAlignment = Alignment.CenterHorizontally
            ) {
            Column(modifier = Modifier.padding(8.dp)) {
                NoteItem(navController = navController)
            }
        }
    }
}

@Composable
fun NoteItem(navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(NoteRoute.NoteScreen.route)
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
                text = "Title test",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Subtitle",
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