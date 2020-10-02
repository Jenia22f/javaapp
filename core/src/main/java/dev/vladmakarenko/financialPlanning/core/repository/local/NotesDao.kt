package dev.vladmakarenko.financialPlanning.core.repository.local

import androidx.lifecycle.LiveData
import androidx.room.*
import dev.vladmakarenko.financialPlanning.core.model.Note

@Dao
interface NotesDao {
    @Query("SELECT * FROM notes")
    fun notes(): LiveData<List<Note>>

    @Delete
    suspend fun deleteNote(note: Note)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)
}