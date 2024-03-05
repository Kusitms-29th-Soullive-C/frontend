package com.example.soullive.ui.input_search

import SearchResultAdapter
import SelectedItemsAdapter
import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.soullive.R
import android.view.inputmethod.EditorInfo
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.AppCompatButton
import com.example.soullive.databinding.FragmentInputStep4Binding

class InputStep4Fragment : Fragment() {
    private var _binding: FragmentInputStep4Binding? = null
    private lateinit var searchResultAdapter: SearchResultAdapter
    private lateinit var selectedItemsAdapter: SelectedItemsAdapter
    private lateinit var button: AppCompatButton

    private val binding get() = _binding!!

    val dummyData = listOf(
        mapOf(
            "이름" to "잇섭",
            "직업" to "유튜버",
            "키워드" to listOf("정보통", "매력있는", "신뢰도 높은"),
            "이미지" to R.drawable.ic_itsub
        ),
        mapOf(
            "이름" to "이선빈",
            "직업" to "배우",
            "키워드" to listOf("다재다능한", "매력적인", "예술적인")
        ),
        mapOf(
            "이름" to "이수현",
            "직업" to "가수",
            "키워드" to listOf("감성적인", "매력있는", "독특한 목소리")
        ),
        mapOf(
            "이름" to "고윤정",
            "직업" to "모델",
            "키워드" to listOf("스타일리시한", "매력적인", "패션 센스"),
            "이미지" to R.drawable.ic_goyoonjung
        ),
        mapOf(
            "이름" to "고윤아",
            "직업" to "프로그래머",
            "키워드" to listOf("혁신적인", "지적인", "문제 해결사")
        ),
        mapOf(
            "이름" to "고준희",
            "직업" to "디자이너",
            "키워드" to listOf("창의적인", "예술적인", "세심한 주의")
        ),
        mapOf(
            "이름" to "고말숙",
            "직업" to "작가",
            "키워드" to listOf("상상력 풍부한", "문학적인", "섬세한 표현력")
        )
    )


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentInputStep4Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button = view.findViewById(R.id.btn_search_next)
        binding.inputSearchSelectedModel.visibility = View.GONE
        binding.inputSearchSelectedCount.visibility = View.GONE
        binding.root.setOnClickListener {
            hideKeyboard()
        }
        setBackButton()
        setProgressBar()
        setCancleButton()
        updateButtonState()
        setupSearchView()
        setupEditFocus()
        setupKeyboardVisibilityListener()
        setupRecyclerView()
        nextButton()
    }

    private fun setBackButton() {
        binding.inputSearchToolbar.setNavigationOnClickListener {
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

    private fun setProgressBar() {
        binding.searchProgress.progress = 300
    }

    private fun setCancleButton() {
        binding.searchCancel.setOnClickListener {
            findNavController().navigate(R.id.action_inputStep4_to_navigation_home)
        }
    }

    private fun setupSearchView() {
        binding.inputSearchResult.visibility = View.GONE
        binding.inputSearchField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (s.isNullOrEmpty()) {
                    binding.inputSearchResult.visibility = View.GONE
                    binding.btnSearchNext.visibility = View.GONE
                } else {
                    filterData(s.toString())
                    binding.inputSearchResult.visibility = View.VISIBLE
                    binding.btnSearchNext.visibility = View.VISIBLE
                }
            }
        })

        binding.inputSearchField.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_SEARCH || event?.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_ENTER) {
                selectItemIfMatched(binding.inputSearchField.text.toString())
                true
            } else {
                false
            }
        }
    }

    private fun setupEditFocus() {
        binding.inputSearchField.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.inputSearchField.setBackgroundResource(R.drawable.bg_search_edittext_focused)
            } else {
                binding.inputSearchField.setBackgroundResource(R.drawable.bg_search_edittext_normal)
            }
        }
    }

    private fun setupKeyboardVisibilityListener() {
        val rootView = activity?.findViewById<View>(android.R.id.content) ?: return
        val btnSearchNext = binding.btnSearchNext

        rootView.viewTreeObserver.addOnGlobalLayoutListener {
            val rect = Rect()
            rootView.getWindowVisibleDisplayFrame(rect)
            val screenHeight = rootView.height

            val keypadHeight = screenHeight - rect.bottom

            if (keypadHeight > screenHeight * 0.7) {
                btnSearchNext.visibility = View.GONE
            } else {
                btnSearchNext.visibility = View.VISIBLE
            }
        }
    }


    private fun setupRecyclerView() {
        searchResultAdapter = SearchResultAdapter { item, isSelected ->
            if (isSelected && selectedItemsAdapter.itemCount < 5) {
                selectedItemsAdapter.addItem(item, requireContext())
                hideKeyboard()
            } else if (!isSelected) {
                selectedItemsAdapter.removeItem(item, requireContext())
                hideKeyboard()
            }

            if (selectedItemsAdapter.itemCount > 0) {
                binding.inputSearchSelectedModel.visibility = View.VISIBLE
                binding.inputSearchSelectedCount.visibility = View.VISIBLE
                binding.btnSearchNext.isEnabled = true
            } else {
                binding.inputSearchSelectedModel.visibility = View.GONE
                binding.inputSearchSelectedCount.visibility = View.GONE
                binding.btnSearchNext.isEnabled = false
            }
            updateSelectedItemCount()
        }

        binding.inputSearchResult.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = searchResultAdapter
        }

        selectedItemsAdapter = SelectedItemsAdapter { item, isSelected ->
            if (isSelected && selectedItemsAdapter.itemCount < 5) {
            } else if (!isSelected) {
                selectedItemsAdapter.removeItem(item, requireContext())
            }
            if (selectedItemsAdapter.itemCount > 0) {
                binding.inputSearchSelectedModel.visibility = View.VISIBLE
                binding.inputSearchSelectedCount.visibility = View.VISIBLE
                binding.btnSearchNext.isEnabled = true
            } else {
                binding.inputSearchSelectedModel.visibility = View.GONE
                binding.inputSearchSelectedCount.visibility = View.GONE
                binding.btnSearchNext.isEnabled = false
            }
            updateSelectedItemCount()
        }
        binding.inputSearchSelectedItems.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = selectedItemsAdapter
        }
    }

    private fun updateSelectedItemCount() {
        val itemCount = selectedItemsAdapter.itemCount
        val text = "$itemCount/5"
        binding.inputSearchSelectedCount.text = text
    }


    private fun selectItemIfMatched(text: String) {
        val matchedItem = dummyData.firstOrNull {
            (it["이름"] as? String)?.contains(text, ignoreCase = true) ?: false
        }

        matchedItem?.let { item ->
            val isSelected = false
            if (!isSelected) {
                selectedItemsAdapter.addItem(item, requireContext())
            }
        }
    }


    private fun filterData(text: String) {
        val filteredList = dummyData.filter {
            (it["이름"] as? String)?.contains(text, ignoreCase = true) ?: false
        }
        searchResultAdapter.setData(filteredList)
    }

    private fun onSearchView() {

    }


    private fun updateButtonState() {
        val isConditionMet = true
        button.isEnabled = isConditionMet
    }

    private fun nextButton() {
        binding.btnSearchNext.setOnClickListener {
            findNavController().navigate(R.id.action_inputStep4_to_inputStep5)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}