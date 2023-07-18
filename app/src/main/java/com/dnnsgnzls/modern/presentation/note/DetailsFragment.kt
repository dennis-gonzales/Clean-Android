package com.dnnsgnzls.modern.presentation.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dnnsgnzls.core.data.Note
import com.dnnsgnzls.modern.R
import com.dnnsgnzls.modern.databinding.FragmentDetailsBinding
import com.dnnsgnzls.modern.framework.viewmodels.NoteViewModel

private const val NEW_NOTE_ID = 0L

class DetailsFragment : Fragment(), MenuProvider {
    private val viewModel by viewModels<NoteViewModel>()

    private lateinit var navController: NavController

    private var _binding: FragmentDetailsBinding? = null
    private val binding
        get() = _binding!!

    private var editingNote: Note? = null
    private var noteId: Long = NEW_NOTE_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args by navArgs<DetailsFragmentArgs>()
        val existingNoteId = args.noteId

        if (existingNoteId != NEW_NOTE_ID) {
            this.noteId = existingNoteId
            viewModel.getNote(existingNoteId)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val view = binding.root

        navController = findNavController()

        initializeViews()
        observeViewModels()

        return view
    }

    private fun initializeViews() {
        requireActivity().addMenuProvider(this, viewLifecycleOwner)

        binding.floatingActionButton.setOnClickListener {
            if (binding.titleEditText.text.isNullOrEmpty()) {
                Toast.makeText(activity, "Please provide a title.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (binding.contentMultiLineText.text.isNullOrEmpty()) {
                Toast.makeText(activity, "Please provide a content.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val time = System.currentTimeMillis()

            val newNote = Note(
                title = binding.titleEditText.text.toString(),
                content = binding.contentMultiLineText.text.toString(),
                creationDate = time,
                updateTime = time,
                id = noteId
            )

            viewModel.saveNote(newNote)

            navController.popBackStack()
        }
    }

    private fun observeViewModels() {
        viewModel.note.observe(viewLifecycleOwner) { note ->
//            .title = note.title
            editingNote = note
            binding.titleEditText.setText(note.title)
            binding.contentMultiLineText.setText(note.content)
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_note, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.delete_note_menu -> {
                editingNote?.let {
                    viewModel.deleteNote(it)
                    navController.popBackStack()
                    return true
                }
            }
        }

        return false
    }
}