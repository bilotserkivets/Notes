package com.example.notes.utils

import com.example.notes.database.room.dao.DatabaseRepository

const val TYPE_ROOM = "type_room"
const val TYPE_FIREBASE = "type_firebase"

lateinit var REPOSITORY: DatabaseRepository

object Constants {
    object Key {
        const val WHAT_WILL_YOU_WIN = "What will you win?"
        const val ROOM_DATABASE = "ROOM DATABASE"
        const val FIREBASE = "FIREBASE"
        const val TITLE = "Title"
        const val SUBTITLE = "Subtitle"
        const val ADD_NOTE = "Add note"
        const val ENTERED_YOU_NOTE = "Entered you note"
        const val MY_APP_NOTES = "My app Notes"
    }
    object Screens {
        const val START_SCREEN = "start_screen"
        const val MAIN_SCREEN = "main_screen"
        const val NOTE_SCREEN = "note_screen"
        const val ADD_SCREEN = "add_screen"
    }
}