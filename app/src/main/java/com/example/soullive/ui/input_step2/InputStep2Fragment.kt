package com.example.soullive.ui.input_step2

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.navigation.fragment.findNavController
import com.example.soullive.R
import com.example.soullive.databinding.FragmentInputStep2Binding

data class KeywordModel(
    val keyword: String,
) {
    companion object {
        val KeywordList = mutableListOf(
            KeywordModel("프리미엄"),
            KeywordModel("프리미엄라인인 S시리즈 강조"),
            KeywordModel("새로 들어간 AI "),
            KeywordModel("새로 들어간 AI통역 기능 각인"),
        )
    }
}

interface KeywordDeleteListener {
    fun onKeywordDeleted()
}

class InputStep2Fragment : Fragment(), KeywordDeleteListener {

    private var _binding: FragmentInputStep2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentInputStep2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBackButton()
        setProgressBar()
        setCancleButton()
        checkNextButtonState()
        setupClickListeners()
        binding.recyclerView.adapter = KeywordAdapter(KeywordModel.KeywordList, this)
        binding.root.setOnClickListener {
            hideKeyboard()
        }
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

    private fun setCancleButton() {
        binding.step2Cancle.setOnClickListener {
            findNavController().navigate(R.id.action_inputStep2_to_navigation_home)
        }
    }

    private fun checkNextButtonState() {
        if (KeywordModel.KeywordList.isEmpty()) {
            binding.btnStep2Next.isEnabled = false
        } else {
            binding.btnStep2Next.isEnabled = true
        }
    }

    private fun setupClickListeners() {
        binding.etInput1.setOnFocusChangeListener { v, hasFocus ->
            // 포커스가 주어졌을 때 동작
            if (hasFocus) {
                binding.input1.isPressed = true
                binding.btnTvDelete.visibility = View.VISIBLE
            } else {
                binding.input1.isPressed = false
                binding.btnTvDelete.visibility = View.INVISIBLE
            }
        }
        binding.etInput1.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE ||
                (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN)
            ) {
                val inputText = binding.etInput1.text.toString()
                if (inputText.isNotBlank()) {
                    KeywordModel.KeywordList.add(KeywordModel(inputText))
                    binding.recyclerView.adapter?.notifyItemInserted(KeywordModel.KeywordList.size - 1)
                    binding.etInput1.text = null // 입력창 초기화
                    hideKeyboard()
                    checkNextButtonState()
                    return@setOnEditorActionListener true
                }
            }
            return@setOnEditorActionListener false
        }
        binding.btnStep2Next.setOnClickListener {
            findNavController().navigate(R.id.action_inputStep2_to_inputStep3)
        }
        binding.btnTvDelete.setOnClickListener {
            binding.etInput1.text = null // 입력창 초기화
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

    override fun onKeywordDeleted() {
        checkNextButtonState()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}