package com.example.soullive.ui.input_step2

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.navigation.fragment.findNavController
import com.example.soullive.R
import com.example.soullive.databinding.FragmentInputStep2Binding
import com.google.android.material.internal.ViewUtils.hideKeyboard


class InputStep2Fragment : Fragment() {

    private var _binding: FragmentInputStep2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentInputStep2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBackButton()
        setProgressBar()
        setupClickListeners()
        binding.root.setOnClickListener {
            hideKeyboard()
        }
        editTextChangedListener()
        nextButton()
    }


    private fun setBackButton() {
        binding.step2Toolbar.setNavigationOnClickListener {
            val navController = findNavController()
            navController.navigateUp()
        }
    }

    private fun setProgressBar() {
        binding.progressHorizontal.progress = 100
    }

    private fun setupClickListeners() {
        binding.etInput1.setOnFocusChangeListener { v, hasFocus ->
            // 포커스가 주어졌을 때 동작
            if (hasFocus) {
                binding.input1.isPressed = true
            } else {
                binding.input1.isPressed = false
            }
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

        // 값 변경 시 실행되는 함수
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            // 버튼 활성화 여부
            activateRegisterBtn()
        }

        override fun afterTextChanged(s: Editable?) {}
    }

    private fun activateRegisterBtn() {
        with(binding) {
            val isInputWritten1 = etInput1.text.toString()

            if (isInputWritten1 != "") {
                binding.btnStep2Next.isEnabled = true
            } else {
                binding.btnStep2Next.isEnabled = false
            }
        }
    }

    private fun editTextChangedListener() {
        with(binding) {
            val watcher = MyEditWatcher()
            etInput1.addTextChangedListener(watcher)
        }
    }

    private fun nextButton() {
        binding.btnStep2Next.setOnClickListener {
            findNavController().navigate(R.id.action_inputStep2_to_inputStep3)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}