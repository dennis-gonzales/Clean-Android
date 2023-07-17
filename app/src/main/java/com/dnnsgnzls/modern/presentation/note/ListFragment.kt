package com.dnnsgnzls.modern.presentation.note

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.dnnsgnzls.modern.R
import com.dnnsgnzls.modern.databinding.FragmentListBinding

class ListFragment : Fragment() {
    private val viewModel by viewModels<NoteViewModel>()

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

        return view
    }

    private fun initializeViews() {
        binding.floatingActionButton.setOnClickListener {
            navController.navigate(R.id.action_listFragment_to_detailsFragment)
        }
    }
}