package dev.vladmakarenko.financialPlanning.core

import android.app.Application
import dev.vladmakarenko.financialPlanning.core.di.DaggerCoreComponent

class App: Application() {
    val coreComponent by lazy {
        DaggerCoreComponent.factory().create(this)
    }
}