package com.example.soullive.ui.input_search

import SearchResultAdapter
import SelectedItemsAdapter
import android.graphics.Rect
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.soullive.databinding.FragmentInputSerachBinding
import androidx.recyclerview.widget.LinearLayoutManager


class InputSerachFragment : Fragment() {
    private var _binding: FragmentInputSerachBinding? = null
    private lateinit var searchResultAdapter: SearchResultAdapter
    private lateinit var selectedItemsAdapter: SelectedItemsAdapter

    private val binding get() = _binding!!

    val dummyData = listOf(
        mapOf(
            "이름" to "잇섭",
            "직업" to "기술 리뷰어",
            "키워드" to listOf("정보통", "매력있는", "신뢰도 높은")
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
            "키워드" to listOf("스타일리시한", "매력적인", "패션 센스")
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
        savedInstanceState: Bundle?
    ): View{
        _binding = FragmentInputSerachBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBackButton()
        setProgressBar()
        setupSearchView()
        setupKeyboardVisibilityListener()
        setupRecyclerView()
    }



    private fun setupRecyclerView() {
        searchResultAdapter = SearchResultAdapter { item, isSelected ->
            if (isSelected) {
                selectedItemsAdapter.addItem(item)
            } else {
                selectedItemsAdapter.removeItem(item)
            }
        }

        binding.inputSearchResult.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = searchResultAdapter
        }

        selectedItemsAdapter = SelectedItemsAdapter()
        binding.inputSearchSelectedItems.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = selectedItemsAdapter
        }
    }

    private fun setupSearchView() {
        binding.inputSearchField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (s.isNullOrEmpty()) {
                    binding.inputSearchResult.visibility = View.GONE
                } else {
                    filterData(s.toString())
                    binding.inputSearchResult.visibility = View.VISIBLE
                }
            }
        })
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


    private fun filterData(text: String) {
        val filteredList = dummyData.filter {
            (it["이름"] as? String)?.contains(text, ignoreCase = true) ?: false
        }
        searchResultAdapter.setData(filteredList)
    }
    private fun setBackButton() {
        binding.inputSearchToolbar.setNavigationOnClickListener {
            val navController = findNavController()
            navController.navigateUp()
        }
    }

    private fun onSearchView(){

    }

    private fun setProgressBar() {
        binding.searchProgress.progress = 300
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}