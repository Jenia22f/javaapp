package dev.vladmakarenko.financialPlanning.core.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.vladmakarenko.financialPlanning.core.model.Note

@Database(entities = [Note::class], version = NotesDatabase.VERSION)
abstract class NotesDatabase: RoomDatabase() {
    companion object{
        const val VERSION = 1
    }

    abstract fun notesDao(): NotesDao


}