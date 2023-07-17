package com.dnnsgnzls.modern.presentation.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.dnnsgnzls.modern.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {
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

        return view
    }

    private fun initializeViews() {
        binding.floatingActionButton.setOnClickListener {
            navController.popBackStack()
        }
    }
}