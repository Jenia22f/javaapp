package dev.vladmakarenko.financialPlanning.plug.ui.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.vladmakarenko.financialPlanning.core.model.Note
import dev.vladmakarenko.financialPlanning.core.repository.local.NotesDao
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class NotesViewModel(
    private val notesDao: NotesDao
) : ViewModel() {

    fun getNotes(): LiveData<List<Note>>{
        return notesDao.notes()
    }

    fun deleteNote(note: Note){
        viewModelScope.launch(IO) {
            notesDao.deleteNote(note)
        }
    }
}