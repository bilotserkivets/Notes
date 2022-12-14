package com.example.notes

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
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
import com.example.notes.utils.TYPE_ROOM

@Composable
fun StartScreen(navController: NavHostController, viewModel: MainViewModel){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Blue),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = Constants.Key.WHAT_WILL_YOU_WIN,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                viewModel.initDatabase(TYPE_ROOM) {
                    navController.navigate(NoteRoute.MainScreen.route)
                }
            },
            modifier = Modifier.width(200.dp),
            colors = ButtonDefaults
                .buttonColors(backgroundColor = Blue_button, contentColor = Color.White)
        ) {
            Text(text = Constants.Key.ROOM_DATABASE)
        }
        Button(
            onClick = {
                /*TODO*/
            },
            modifier = Modifier.width(200.dp),
            colors = ButtonDefaults
                .buttonColors(backgroundColor = Blue_button, contentColor = Color.White)
        ) {
            Text(text = Constants.Key.FIREBASE)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StartScreenPrev() {
    val context = LocalContext.current
    val mViewModel: MainViewModel =
        viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
    StartScreen(navController = rememberNavController(), viewModel = mViewModel)
}