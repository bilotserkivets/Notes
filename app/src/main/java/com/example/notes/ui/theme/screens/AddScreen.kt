package com.example.notes

import android.app.Application
import android.provider.Telephony
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
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
fun AddScreen(navController: NavHostController, viewModel: MainViewModel){
    var title by remember { mutableStateOf("") }
    var subtitle by remember { mutableStateOf("") }
    var isButtonEnabled by remember { mutableStateOf(false) }
    
    Scaffold() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            ) {
            Text(
                text = Constants.Key.ENTERED_YOU_NOTE,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            OutlinedTextField(
                value = title, 
                onValueChange = {
                    title = it
                    isButtonEnabled = title.isNotEmpty() && subtitle.isNotEmpty()
                },
                isError = title.isEmpty(),
                label = {
                    Text(text = Constants.Key.TITLE)
                }
                )
            OutlinedTextField(
                value = subtitle,
                onValueChange = {
                    subtitle = it
                    isButtonEnabled = title.isNotEmpty() && subtitle.isNotEmpty()
                },
                isError = subtitle.isEmpty(),
                label = {
                    Text(text = Constants.Key.SUBTITLE)
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                enabled = isButtonEnabled,
                onClick = {
                    viewModel.addNote(note = Note(title = title, subtitle = subtitle)) {
                        navController.navigate(NoteRoute.MainScreen.route)
                    }
                },
                colors = ButtonDefaults
                    .buttonColors(backgroundColor = Blue_button, contentColor = Color.White)
                ) {
                Text(text = Constants.Key.ADD_NOTE)
            }
        }
        
    }
}

@Preview(showBackground = true)
@Composable
fun AddScreenPrev() {
    val context = LocalContext.current
    val mViewModel: MainViewModel =
        viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
    AddScreen(navController = rememberNavController(), viewModel = mViewModel)
}