package com.example.soullive.ui.fitness

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.soullive.R
import com.example.soullive.databinding.FragmentFitnessBinding

class FitnessFragment : Fragment() {

    private var _binding: FragmentFitnessBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFitnessBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fitnessBtn.setOnClickListener {
            findNavController().navigate(R.id.action_fitness_to_inputStart)
        }
        binding.ivFitnessSearch.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_fitness_to_outputDetail)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}