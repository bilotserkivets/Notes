package com.example.notes

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.example.notes.database.room.AppRoomDatabase
import com.example.notes.database.room.repository.RoomRepository
import com.example.notes.utils.REPOSITORY
import com.example.notes.utils.TYPE_ROOM

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val context = application

    fun initDatabase(type: String, onSuccess: () -> Unit) {
        val dao = AppRoomDatabase.getInstance(context).getRoomDao()

        when(type) {
            TYPE_ROOM -> {
                REPOSITORY = RoomRepository(dao)
                onSuccess()
            }
        }
    }

}