package dev.vladmakarenko.financialPlanning.core.di

import dagger.Binds
import dagger.Module
import dev.vladmakarenko.financialPlanning.core.repository.local.Storage
import dev.vladmakarenko.financialPlanning.core.repository.local.StorageImpl
import javax.inject.Singleton

@Module
abstract class StorageModule {
    @Singleton
    @Binds
    abstract fun bindStorage(storageImpl: StorageImpl): Storage
}