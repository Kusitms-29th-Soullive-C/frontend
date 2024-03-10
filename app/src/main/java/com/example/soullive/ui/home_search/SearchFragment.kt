package com.example.soullive.ui.home_search

import SearchResultAdapter
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
import com.example.soullive.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var searchResultAdapter: HomeSearchAdapter


    val SearchDummyData = listOf(
        mapOf(
            "이름" to "잇섭",
            "직업" to "유튜버",
            "이미지" to R.drawable.ic_itsub
        ),
        mapOf(
            "이름" to "고윤정",
            "직업" to "모델",
            "이미지" to R.drawable.ic_goyoonjung
        ),
        mapOf(
            "이름" to "고수",
            "직업" to "배우",
            "이미지" to R.drawable.ic_goyoonjung
        ),
        mapOf(
            "이름" to "수지",
            "직업" to "배우",
            "이미지" to R.drawable.ic_goyoonjung
        ),
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBackButton()
        setupRecyclerView()

        binding.homeSearchResult.visibility = View.GONE

        binding.inputSearchField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s.isNullOrEmpty()) {
                    binding.homeSearchResult.visibility = View.GONE
                } else {
                    filterData(s.toString())
                    binding.homeSearchResult.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun setBackButton() {
        binding.searchToolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_search_to_navigation_home)
        }
    }

    private fun setupRecyclerView() {
        searchResultAdapter = HomeSearchAdapter(SearchDummyData)
        binding.homeSearchResult.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = searchResultAdapter
        }
    }

    private fun filterData(text: String) {
        val filteredList = SearchDummyData.filter {
            (it["이름"] as? String)?.contains(text, ignoreCase = true) ?: false
        }
        searchResultAdapter.updateData(filteredList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}