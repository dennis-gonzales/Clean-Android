package com.dnnsgnzls.modern.presentation.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
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
        binding.recyclerView.apply {
            adapter = noteAdapter
            setHasFixedSize(true)
        }

        binding.floatingActionButton.setOnClickListener {
            navController.navigate(R.id.action_listFragment_to_detailsFragment)
        }
    }

    private fun observeViewModels() {
        viewModel.getAllNotes()

        viewModel.noteList.observe(viewLifecycleOwner) {noteList ->
            Toast.makeText(requireContext(), "${noteList.size}", Toast.LENGTH_SHORT).show()
            noteAdapter.updateList(noteList)
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onClick(view: View, note: Note) {
        navController.navigate(R.id.action_listFragment_to_detailsFragment)
    }
}