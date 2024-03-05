package com.example.soullive.ui.event

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.soullive.R
import com.example.soullive.databinding.FragmentSubscribeBinding

class SubscribeFragment : Fragment() {

    private var _binding: FragmentSubscribeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSubscribeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBackButton()
        checkNextButtonState()
        setupClickListeners()
        nextButton()
    }

    private fun setBackButton() {
        binding.subscribeToolbar.setNavigationOnClickListener {
            val navController = findNavController()
            navController.navigateUp()
        }
    }

    private fun setupClickListeners() {
        binding.subscribe1.setOnClickListener {
            it.isSelected = true
            binding.subscribe2.isSelected = false
            checkNextButtonState()
        }
        binding.subscribe2.setOnClickListener {
            it.isSelected = true
            binding.subscribe1.isSelected = false
            checkNextButtonState()
        }
    }

    private fun checkNextButtonState() {
        val isAnySubscribeSelected =
            listOf(binding.subscribe1, binding.subscribe2).any { it.isSelected }

        binding.btnSubscribeNext.isEnabled = isAnySubscribeSelected
        binding.btnSubscribeNext.setOnClickListener {
            if (binding.btnSubscribeNext.isEnabled)
                nextButton()
        }
    }

    private fun nextButton() {
        binding.btnSubscribeNext.setOnClickListener {
            findNavController().navigate(R.id.action_subscribe_to_navigation_home)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}