package com.example.notes

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.notes.database.room.AppRoomDatabase
import com.example.notes.database.room.repository.RoomRepository
import com.example.notes.model.Note
import com.example.notes.utils.REPOSITORY
import com.example.notes.utils.TYPE_ROOM
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val context = application


    fun initDatabase(type: String, onSuccess: () -> Unit) {
        val dao = AppRoomDatabase.getInstance(context).getRoomDao()
        when(type) {
            TYPE_ROOM -> {
                REPOSITORY = RoomRepository(dao)
                Log.d("initDatabase", "type database: $type")
                onSuccess()
            }
        }
    }

    fun addNote(note: Note, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.create(note = note) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun getAllNotes() : LiveData<List<Note>> {
        return  REPOSITORY.readAll
    }

    fun deleteNote(note: Note, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.delete(note = note) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun updateNote(note: Note, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.update(note = note) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }
}