package dev.vladmakarenko.financialPlanning.core.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dev.vladmakarenko.financialPlanning.core.repository.local.NotesDao
import dev.vladmakarenko.financialPlanning.core.repository.local.Storage
import dev.vladmakarenko.financialPlanning.core.repository.remote.NetworkService
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NotesModule::class, NetworkModule::class, StorageModule::class]
)
interface CoreComponent {

    fun storage(): Storage

    fun notesDao(): NotesDao

    fun networkService(): NetworkService

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): CoreComponent
    }
}