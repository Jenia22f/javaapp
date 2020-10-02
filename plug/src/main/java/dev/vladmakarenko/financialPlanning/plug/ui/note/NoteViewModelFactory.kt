package dev.vladmakarenko.financialPlanning.plug.ui.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.vladmakarenko.financialPlanning.core.repository.local.NotesDao
import dev.vladmakarenko.financialPlanning.plug.di.PlugScope
import java.lang.IllegalArgumentException
import javax.inject.Inject

@PlugScope
class NoteViewModelFactory @Inject constructor(private val notesDao: NotesDao) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            return modelClass.getConstructor(NotesDao::class.java).newInstance(notesDao)
        } else {
            throw IllegalArgumentException("Invalid class!")
        }
    }
}