package dev.vladmakarenko.financialPlanning.plug.ui.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.vladmakarenko.financialPlanning.core.repository.local.NotesDao
import dev.vladmakarenko.financialPlanning.plug.di.PlugScope
import javax.inject.Inject

@PlugScope
class NotesViewModelFactory @Inject constructor(private val notesDao: NotesDao): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotesViewModel::class.java)){
            return modelClass.getConstructor(NotesDao::class.java).newInstance(notesDao)
        } else {
            throw IllegalArgumentException("Illegal class!")
        }
    }
}