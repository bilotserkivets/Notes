package com.example.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import com.example.notes.navigation.NoteNavHost
import com.example.notes.ui.theme.NotesTheme
import com.example.notes.utils.Constants

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
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
                NoteNavHost()
            }
        }
    }
}
