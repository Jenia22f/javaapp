package dev.vladmakarenko.financialPlanning.core.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dev.vladmakarenko.financialPlanning.core.repository.local.NotesDao
import dev.vladmakarenko.financialPlanning.core.repository.local.NotesDatabase
import javax.inject.Singleton

@Module
class NotesModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): NotesDatabase {
        return Room.databaseBuilder(
            context,
            NotesDatabase::class.java,
            "notes-db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideDao(database: NotesDatabase): NotesDao {
        return database.notesDao()
    }

}