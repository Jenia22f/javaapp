package dev.vladmakarenko.financialPlanning.plug.di

import dagger.Component
import dev.vladmakarenko.financialPlanning.core.di.CoreComponent
import dev.vladmakarenko.financialPlanning.plug.ui.note.NoteFragment
import dev.vladmakarenko.financialPlanning.plug.ui.notes.NotesFragment

@PlugScope
@Component(
    dependencies = [CoreComponent::class]
)
interface PlugComponent {

    @Component.Factory
    interface Factory{
        fun create(coreComponent: CoreComponent): PlugComponent
    }

    fun inject(fragment: NoteFragment)
    fun inject(fragment: NotesFragment)
}