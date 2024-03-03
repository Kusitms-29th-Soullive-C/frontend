package com.example.soullive.ui.input

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.soullive.R
import com.example.soullive.databinding.FragmentInputStep3Binding

class InputStep3Fragment : Fragment() {

    private var _binding: FragmentInputStep3Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentInputStep3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBackButton()
        setCancleButton()
        setProgressBar()
        setupClickListeners()
    }

    private fun setBackButton() {
        binding.targetToolbar.setNavigationOnClickListener {
            val navController = findNavController()
            navController.navigateUp()
        }
    }

    private fun setCancleButton() {
        binding.targetCancle.setOnClickListener {
            listOf(binding.btnMan, binding.btnWoman, binding.btnGender).forEach {
                it.isSelected = false
            }
            listOf(
                binding.btnAge0,
                binding.btnAge10,
                binding.btnAge20,
                binding.btnAge30,
                binding.btnAge40,
                binding.btnAge50,
                binding.btnAge60,
                binding.btnAge
            ).forEach { it.isSelected = false }

            checkNextButtonState()
            findNavController().navigate(R.id.action_inputStep3_to_navigation_home)
        }
    }

    private fun setProgressBar() {
        binding.inputStep3Progress.progress = 200
    }

    private fun setupClickListeners() {
        val genderButtons = listOf(binding.btnMan, binding.btnWoman)
        genderButtons.forEach { button ->
            button.setOnClickListener {
                button.isSelected = !button.isSelected
                binding.btnGender.isSelected = false
                checkNextButtonState()
            }
        }

        binding.btnGender.setOnClickListener {
            it.isSelected = !it.isSelected
            if (it.isSelected) {
                binding.btnMan.isSelected = false
                binding.btnWoman.isSelected = false
            }
            checkNextButtonState()
        }

        val ageButtons = listOf(
            binding.btnAge0,
            binding.btnAge10,
            binding.btnAge20,
            binding.btnAge30,
            binding.btnAge40,
            binding.btnAge50,
            binding.btnAge60
        )
        ageButtons.forEach { button ->
            button.setOnClickListener {
                button.isSelected = !button.isSelected
                binding.btnAge.isSelected = false
                checkNextButtonState()
            }
        }

        binding.btnAge.setOnClickListener {
            it.isSelected = !it.isSelected
            if (it.isSelected) {
                binding.btnAge0.isSelected = false
                binding.btnAge10.isSelected = false
                binding.btnAge20.isSelected = false
                binding.btnAge30.isSelected = false
                binding.btnAge40.isSelected = false
                binding.btnAge50.isSelected = false
                binding.btnAge60.isSelected = false
            }
            checkNextButtonState()
        }

        binding.btnTargetNext.isEnabled = false
    }

    private fun checkNextButtonState() {
        val isAnyGenderSelected =
            listOf(binding.btnMan, binding.btnWoman, binding.btnGender).any { it.isSelected }
        val isAnyAgeSelected = listOf(
            binding.btnAge0,
            binding.btnAge10,
            binding.btnAge20,
            binding.btnAge30,
            binding.btnAge40,
            binding.btnAge50,
            binding.btnAge60,
            binding.btnAge
        ).any { it.isSelected }

        binding.btnTargetNext.isEnabled = isAnyGenderSelected && isAnyAgeSelected
        binding.btnTargetNext.setOnClickListener {
            if (binding.btnTargetNext.isEnabled)
                nextButton()
        }
    }

    private fun nextButton() {
        binding.btnTargetNext.setOnClickListener {
            findNavController().navigate(R.id.action_inputStep3_to_inputStep4)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}