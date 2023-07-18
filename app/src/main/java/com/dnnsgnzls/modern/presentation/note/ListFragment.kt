package com.dnnsgnzls.modern.presentation.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dnnsgnzls.core.data.Note
import com.dnnsgnzls.modern.R
import com.dnnsgnzls.modern.databinding.FragmentListBinding
import com.dnnsgnzls.modern.framework.viewmodels.ListViewModel
import com.dnnsgnzls.modern.presentation.adapter.INoteClick
import com.dnnsgnzls.modern.presentation.adapter.NoteAdapter

class ListFragment : Fragment(), INoteClick {
    private val viewModel by viewModels<ListViewModel>()
    private val noteAdapter: NoteAdapter by lazy {
        NoteAdapter(arrayListOf(), this, viewModel)
    }

    private lateinit var navController: NavController

    private var _binding: FragmentListBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root

        navController = findNavController()

        initializeViews()
        observeViewModels()

        return view
    }

    private fun initializeViews() {
        binding.progressBar.visibility = View.VISIBLE

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = noteAdapter
            setHasFixedSize(true)
        }

        binding.floatingActionButton.setOnClickListener {
            val action = ListFragmentDirections
                .actionListFragmentToDetailsFragment("Add New Note")
            navController.navigate(action)
        }
    }

    private fun observeViewModels() {
        viewModel.noteList.observe(viewLifecycleOwner) { noteList ->
            noteAdapter.updateList(noteList)
            binding.recyclerView.alpha = 1F
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onResume() {
        super.onResume()
        binding.recyclerView.alpha = 0.7F
        binding.progressBar.visibility = View.VISIBLE
        viewModel.getAllNotes()
    }

    override fun onClick(view: View, note: Note) {
        if (binding.progressBar.visibility == View.VISIBLE) {
            return
        }

        val action = ListFragmentDirections
            .actionListFragmentToDetailsFragment("Editing ${note.title}")
        action.noteId = note.id
        navController.navigate(action)
    }
}