package com.dnnsgnzls.clean.presentation.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dnnsgnzls.core.data.Note
import com.dnnsgnzls.clean.R
import com.dnnsgnzls.clean.databinding.FragmentListBinding
import com.dnnsgnzls.clean.framework.viewmodels.ListViewModel
import com.dnnsgnzls.clean.presentation.adapter.INoteClick
import com.dnnsgnzls.clean.presentation.adapter.NoteAdapter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ListFragment : Fragment(), INoteClick {
    private val viewModel by viewModels<ListViewModel>()
    private val noteAdapter: NoteAdapter by lazy {
        NoteAdapter(emptyList(), this, viewModel)
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
            layoutManager = LinearLayoutManager(context)
            adapter = noteAdapter
            setHasFixedSize(true)

            val animation = AnimationUtils.loadLayoutAnimation(
                context,
                R.anim.layout_animation_fall_down
            )
            layoutAnimation = animation
        }

        binding.floatingActionButton.setOnClickListener {
            val action = ListFragmentDirections
                .actionListFragmentToDetailsFragment("Add New Note")
            navController.navigate(action)
        }
    }

    private fun observeViewModels() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.noteList.collect { noteList ->
                    noteAdapter.updateList(noteList)

                    if (noteList.isEmpty()) {
                        binding.emptyNoteTv.visibility = View.VISIBLE
                    } else {
                        binding.emptyNoteTv.visibility = View.GONE
                        delay(100)
                        binding.recyclerView.smoothScrollToPosition(0)
                    }
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.isLoading.collect { isLoading ->
                    binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
                    binding.recyclerView.alpha = if (isLoading) 0.7F else 1F
                }
            }
        }

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