package dev.vladmakarenko.financialPlanning.plug.ui.notes

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.*
import dev.vladmakarenko.financialPlanning.core.App
import dev.vladmakarenko.financialPlanning.core.model.Note
import dev.vladmakarenko.financialPlanning.plug.databinding.NotesFragmentBinding
import dev.vladmakarenko.financialPlanning.plug.di.DaggerPlugComponent
import javax.inject.Inject

class NotesFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: NotesViewModelFactory
    private lateinit var viewModel: NotesViewModel

    private lateinit var binding: NotesFragmentBinding

    private val diffCallback = object : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return (oldItem.title + oldItem.description) == (newItem.title + newItem.description)
        }
    }

    private val itemTouchHelper = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = false

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (viewHolder is NoteViewHolder){
                viewModel.deleteNote(viewHolder.binding.note!!)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val coreComponent = (requireActivity().application as App).coreComponent

        DaggerPlugComponent
            .factory()
            .create(coreComponent)
            .inject(this)

        viewModel =
            ViewModelProvider(viewModelStore, viewModelFactory)
                .get(NotesViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NotesFragmentBinding.inflate(inflater, container, false)

        val _adapter = NotesAdapter(diffCallback)

        with(binding.notesRecycler) {
            layoutManager = LinearLayoutManager(this@NotesFragment.requireContext())
            adapter = _adapter
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    LinearLayoutManager.VERTICAL
                )
            )
            ItemTouchHelper(itemTouchHelper).attachToRecyclerView(this)
        }

        binding.notesAddNote.setOnClickListener {
            val direction = NotesFragmentDirections.addNote(null)
            findNavController().navigate(direction)
        }

        viewModel.getNotes().observe(viewLifecycleOwner, Observer {
            _adapter.submitList(it)
        })

        return binding.root
    }
}