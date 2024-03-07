package com.example.soullive.ui.output_search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.soullive.R
import com.example.soullive.databinding.FragmentInputStartBinding
import com.example.soullive.databinding.FragmentOutputSearchBinding

class OutputSearchFragment : Fragment() {
    private var _binding: FragmentOutputSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var searchResultAdapter: OutputSearchAdapter

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
            "이미지" to R.drawable.ic_gosu
        ),
        mapOf(
            "이름" to "수지",
            "직업" to "배우",
            "이미지" to R.drawable.model_suji
        ),
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentOutputSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBackButton()
        setupRecyclerView()

        binding.outputSearchResult.visibility = View.GONE

        binding.outputInputSearchField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s.isNullOrEmpty()) {
                    binding.outputSearchResult.visibility = View.GONE
                } else {
                    filterData(s.toString())
                    binding.outputSearchResult.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun setBackButton() {
        binding.outputSearchToolbar.setNavigationOnClickListener {
        }
    }

    private fun updateRecyclerView(filteredData: List<Map<String, Any>>) {
        searchResultAdapter = OutputSearchAdapter(filteredData)
        binding.outputSearchResult.adapter = searchResultAdapter
    }
    private fun setupRecyclerView() {
        searchResultAdapter = OutputSearchAdapter(SearchDummyData)
        binding.outputSearchResult.apply {
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