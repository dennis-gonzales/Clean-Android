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
import com.dnnsgnzls.modern.databinding.FragmentDetailsBinding
import com.dnnsgnzls.modern.framework.viewmodels.NoteViewModel


class DetailsFragment : Fragment() {
    private val viewModel by viewModels<NoteViewModel>()

    private lateinit var navController: NavController

    private var _binding: FragmentDetailsBinding? = null
    private val binding
        get() = _binding!!

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

            viewModel.saveNote(
                Note(
                    title = binding.titleEditText.text.toString(),
                    content = binding.contentMultiLineText.text.toString(),
                    creationDate = time,
                    updateTime = 0L
                )
            )
            navController.popBackStack()
        }
    }

    private fun observeViewModels() {
//        viewModel.note.observe(viewLifecycleOwner) { note ->
//            binding.titleEditText.setText(note.title)
//            binding.contentMultiLineText.setText(note.content)
//        }
    }
}