package com.example.notes

import android.app.Application
import android.provider.ContactsContract
import android.support.v4.app.INotificationSideChannel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.notes.model.Note
import com.example.notes.navigation.NoteRoute
import com.example.notes.ui.theme.Blue_button
import com.example.notes.utils.Constants
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NoteScreen(navController: NavHostController, viewModel: MainViewModel, noteId: String?){
    val notes = viewModel.getAllNotes().observeAsState(listOf()).value
    val note = notes.firstOrNull { it.id == noteId?.toInt() } ?: Note(title = Constants.Key.EMPTY,
        subtitle = Constants.Key.EMPTY
    )
    var title by remember { mutableStateOf(Constants.Key.EMPTY) }
    var subtitle by remember { mutableStateOf(Constants.Key.EMPTY) }
    val coroutineScope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetElevation = 8.dp,
        sheetShape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
        sheetContent = {
            Surface() {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                ) {
                    Text(
                        text = Constants.Key.UPDATE_NOTE,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    OutlinedTextField(
                        value = title,
                        label = {
                            Text(text = Constants.Key.TITLE)
                        },
                        onValueChange = {
                            title  = it
                        },
                        isError = title.isEmpty()
                    )
                    OutlinedTextField(
                        value = subtitle,
                        label = {
                            Text(text = Constants.Key.SUBTITLE)
                        },
                        onValueChange = {
                            subtitle  = it
                        },
                        isError = subtitle.isEmpty()
                    )
                    Button(
                        onClick = {
                            viewModel.updateNote(Note(id = note.id, title = title, subtitle = subtitle)) {
                                navController.navigate(NoteRoute.MainScreen.route)
                            }
                        }
                    ) {
                        Text(text = Constants.Key.UPDATE)
                    }
                }

            }
        }
    ) {
        Scaffold() {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = note.title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = note.subtitle,
                    fontSize = 18.sp
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(onClick = {
                        coroutineScope.launch {
                            title = note.title
                            subtitle = note.subtitle
                            bottomSheetState.show()
                        }
                    },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Blue_button,
                            contentColor = Color.White)
                    ) {
                        Text(text = Constants.Key.UPDATE)
                    }
                    Button(
                        onClick = {
                            viewModel.deleteNote(note = note) {
                                navController.navigate(NoteRoute.MainScreen.route)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Blue_button,
                            contentColor = Color.White)
                    ) {
                        Text(text = Constants.Key.DELETE)
                    }
                }
                Button(onClick = {
                    navController.navigate(NoteRoute.MainScreen.route)
                },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Blue_button,
                        contentColor = Color.White)
                ) {
                    Text(text = Constants.Key.ROW_BCK)
                }
            }
        }
    }

}

//@Preview(showBackground = true)
//@Composable
//fun NoteScreenPrev() {
//    val context = LocalContext.current
//    val mViewModel: MainViewModel =
//        viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
//    NoteScreen(navController = rememberNavController(), viewModel = mViewModel, note = Note)
//}