package dev.vladmakarenko.financialPlanning.plug.ui.note

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import dev.vladmakarenko.financialPlanning.core.App
import dev.vladmakarenko.financialPlanning.core.model.Note
import dev.vladmakarenko.financialPlanning.core.utils.toEditable
import dev.vladmakarenko.financialPlanning.plug.databinding.NoteFragmentBinding
import dev.vladmakarenko.financialPlanning.plug.di.DaggerPlugComponent
import javax.inject.Inject

class NoteFragment : Fragment() {

    val args: NoteFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: NoteViewModelFactory
    private lateinit var viewModel: NoteViewModel

    private lateinit var binding: NoteFragmentBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val coreComponent = (requireActivity().application as App).coreComponent
        DaggerPlugComponent.factory().create(coreComponent).inject(this)

        viewModel =
            ViewModelProvider(viewModelStore, viewModelFactory).get(NoteViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NoteFragmentBinding.inflate(inflater, container, false)

        val note = args.note

        with(binding) {

            note?.let {
                noteTitle.text = note.title.toEditable()
                noteDescription.text = note.description.toEditable()
            }

            noteSave.setOnClickListener {
                val title = noteTitle.text.toString()
                val description = noteDescription.text.toString()

                if (title.isBlank() || description.isBlank()) {
                    Snackbar.make(noteSnackbarContainer, "Type some text", Snackbar.LENGTH_LONG)
                        .show()
                    return@setOnClickListener
                } else {
                    viewModel.insertNote(
                        Note(
                            key = note?.key?:0,
                            title = title,
                            description = description
                        )
                    )

                    val direction = NoteFragmentDirections.saveNote()
                    findNavController().navigate(direction)
                }
            }
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        requireActivity().actionBar?.setDisplayHomeAsUpEnabled(true)
    }

}