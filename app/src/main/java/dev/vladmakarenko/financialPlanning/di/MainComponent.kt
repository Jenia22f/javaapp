package dev.vladmakarenko.financialPlanning.di

import dagger.Component
import dev.vladmakarenko.financialPlanning.core.di.CoreComponent
import dev.vladmakarenko.financialPlanning.ui.MainActivity

@MainScope
@Component(
    dependencies = [CoreComponent::class]
)
interface MainComponent {

    @Component.Factory
    interface Factory{
        fun create(coreComponent: CoreComponent): MainComponent
    }

    fun inject(activity: MainActivity)
}