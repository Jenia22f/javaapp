package dev.vladmakarenko.financialPlanning.plug.ui.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.vladmakarenko.financialPlanning.core.model.Note
import dev.vladmakarenko.financialPlanning.core.repository.local.NotesDao
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class NoteViewModel(private val notesDao: NotesDao) : ViewModel() {
    fun deleteNote(note: Note){
        viewModelScope.launch(IO) {
            notesDao.deleteNote(note)
        }
    }

    fun insertNote(note: Note){
        viewModelScope.launch(IO) {
            notesDao.insertNote(note)
        }
    }
}