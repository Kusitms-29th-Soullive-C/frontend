package com.example.soullive.ui.input

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.fragment.findNavController
import com.example.soullive.R
import com.example.soullive.databinding.FragmentInputStep1Binding
import com.example.soullive.databinding.FragmentInputStep3Binding
import com.google.android.material.internal.ViewUtils.hideKeyboard

class InputStep1Fragment : Fragment() {

    private var _binding: FragmentInputStep1Binding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentInputStep1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnStep1Next.isEnabled = false
        setBackButton()
        setProgressBar()
        setCancleButton()
        setupClickListeners()
        binding.root.setOnClickListener {
            hideKeyboard()
        }
        nextButton()
        editTextChangedListener()
    }

    private fun setBackButton() {
        binding.step1Toolbar.setNavigationOnClickListener {
            val navController = findNavController()
            navController.navigateUp()
        }
    }

    private fun setProgressBar() {
        binding.progressHorizontal.progress = 0
    }

    private fun setCancleButton() {
        binding.step1Cancle.setOnClickListener {
            findNavController().navigate(R.id.action_inputStep1_to_navigation_home)
        }
    }

    private fun setupClickListeners() {
        binding.etInput1.setOnFocusChangeListener { v, hasFocus ->
            // 포커스가 주어졌을 때 동작
            if (hasFocus) {
                binding.input1.isPressed = true
                binding.btnTvDelete1.visibility = View.VISIBLE
            } else {
                binding.input1.isPressed = false
                binding.btnTvDelete1.visibility = View.INVISIBLE
            }
        }
        binding.etInput2.setOnFocusChangeListener { v, hasFocus ->
            // 포커스가 주어졌을 때 동작
            if (hasFocus) {
                binding.input2.isPressed = true
                binding.btnTvDelete2.visibility = View.VISIBLE
            } else {
                binding.input2.isPressed = false
                binding.btnTvDelete2.visibility = View.INVISIBLE
            }
        }
        binding.etInput3.setOnFocusChangeListener { v, hasFocus ->
            // 포커스가 주어졌을 때 동작
            if (hasFocus) {
                binding.input3.isPressed = true
                binding.btnTvDelete3.visibility = View.VISIBLE
            } else {
                binding.input3.isPressed = false
                binding.btnTvDelete3.visibility = View.INVISIBLE
            }
        }
        binding.btnTvDelete1.setOnClickListener {
            binding.etInput1.text = null // 입력창 초기화
        }
        binding.btnTvDelete2.setOnClickListener {
            binding.etInput2.text = null // 입력창 초기화
        }
        binding.btnTvDelete3.setOnClickListener {
            binding.etInput3.text = null // 입력창 초기화
        }
    }

    private fun hideKeyboard() {
        if (activity != null && requireActivity().currentFocus != null) {
            val inputManager: InputMethodManager =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(
                requireActivity().currentFocus?.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }

    inner class MyEditWatcher : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            activateRegisterBtn()
        }

        override fun afterTextChanged(s: Editable?) {}
    }

    private fun activateRegisterBtn() {
        with(binding) {
            val isInputWritten1 = etInput1.text.toString()
            val isInputWritten2 = etInput2.text?.toString()
            val isInputWritten3 = etInput3.text?.toString()

            if (isInputWritten1 != "" && isInputWritten2 != "" && isInputWritten3 != "") {
                binding.btnStep1Next.isEnabled = true
            } else {
                binding.btnStep1Next.isEnabled = false
            }
        }
    }

    private fun editTextChangedListener() {
        with(binding) {
            val watcher = MyEditWatcher()
            etInput1.addTextChangedListener(watcher)
            etInput2.addTextChangedListener(watcher)
            etInput3.addTextChangedListener(watcher)
        }
    }

    private fun nextButton() {
        binding.btnStep1Next.setOnClickListener {
            findNavController().navigate(R.id.action_inputStep1_to_inputStep2)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}