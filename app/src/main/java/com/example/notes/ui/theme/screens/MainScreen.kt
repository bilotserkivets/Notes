package com.example.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notes.ui.theme.Blue

@Composable
fun MainScreen(){
    Scaffold() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Blue),
            horizontalAlignment = Alignment.CenterHorizontally
            ) {
            Column(modifier = Modifier.padding(8.dp)) {
                NoteItem()
            }
        }
    }
}

@Composable
fun NoteItem() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
        ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
            ) {
            Text(
                text = "Title",
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
    MainScreen()
}