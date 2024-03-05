package com.example.soullive.ui.sign_up

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.navigation.fragment.findNavController
import com.example.soullive.R
import com.example.soullive.databinding.FragmentInputStartBinding
import com.example.soullive.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBackButton()
        binding.root.setOnClickListener {
            hideKeyboard()
        }
        setupClickListeners()
        nextButton()
    }

    private fun setBackButton() {
        binding.signUpToolbar.setNavigationOnClickListener {
            val navController = findNavController()
            navController.navigateUp()
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
            } else {
                binding.input2.isPressed = false
            }
        }
        binding.etInput3.setOnFocusChangeListener { v, hasFocus ->
            // 포커스가 주어졌을 때 동작
            if (hasFocus) {
                binding.input3.isPressed = true
                binding.btnTvDelete2.visibility = View.VISIBLE
            } else {
                binding.input3.isPressed = false
                binding.btnTvDelete2.visibility = View.INVISIBLE
            }
        }

        binding.btnTvDelete1.setOnClickListener {
            binding.etInput1.text = null // 입력창 초기화
        }
        binding.btnCertification.setOnClickListener {
            if (binding.btnCertification.text == "인증번호 받기") {
                binding.btnCertification.text = "인증하기"
            } else if (binding.btnCertification.text == "인증하기") {
                binding.btnCertification.isEnabled = false
                binding.btnCertification.text = "인증완료"
            }
        }
        binding.btnTvDelete2.setOnClickListener {
            binding.etInput3.text = null // 입력창 초기화
        }
    }

    private fun nextButton() {
        binding.btnSignUpNext.setOnClickListener {
            findNavController().navigate(R.id.action_signUp_to_eventPopup)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}