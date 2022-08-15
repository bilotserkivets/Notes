package com.example.notes

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notes.navigation.NoteNavHost
import com.example.notes.utils.Constants

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            val mViewModel: MainViewModel =
                viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
            Scaffold(
                topBar = {
                    TopAppBar (
                        title = {
                            Text(text = Constants.Key.MY_APP_NOTES)
                        },
                        contentColor = Color.White,
                        backgroundColor = Color.Blue,
                        elevation = 5.dp
                    )
                }
            ) {
                NoteNavHost(viewModel = mViewModel)
            }
        }
    }
}
