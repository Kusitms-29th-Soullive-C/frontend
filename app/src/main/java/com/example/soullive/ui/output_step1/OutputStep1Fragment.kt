package com.example.soullive.ui.output_step1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.soullive.R
import com.example.soullive.databinding.FragmentOutputStep1Binding
import com.google.android.material.tabs.TabLayoutMediator

data class Model(
    val name: String,
    val job: String,
    val keywords: List<String>,
    val rank: Int,
    val relevance: Int,
    val hotness: Int,
    val negativeIssues: String,
    val ImageText: String,
    val imageResId: Int
)


class OutputStep1Fragment : Fragment() {

    private var _binding: FragmentOutputStep1Binding? = null
    private val binding get() = _binding!!
    private lateinit var viewPager : ViewPager2
    private lateinit var OutputModelAdapter : OutputModelAdapter

    val dummyList = listOf(
        Model("고윤정","배우", listOf("Keyword1", "Keyword2"), 3, 95,3,"성형논란이 있었으나 악의적 편집으로 밝혀짐. 이 사건 때 동창들이 나서서 변호를 해주는 것으로 보아 학창시절 논란은 없을 것으로 판단 됨.","럭셔리", R.drawable.ic_goyoonjung),
        Model("고윤정2","배우", listOf("Keyword1", "Keyword2"), 2, 91,4,"성형논란이 있었으나 악의적 편집으로 밝혀짐. 이 사건 때 동창들이 나서서 변호를 해주는 것으로 보아 학창시절 논란은 없을 것으로 판단 됨.","럭셔리",R.drawable.ic_goyoonjung),
        Model("고윤정3","배우", listOf("Keyword1", "Keyword2"), 5, 33,1,"성형논란이 있었으나 악의적 편집으로 밝혀짐. 이 사건 때 동창들이 나서서 변호를 해주는 것으로 보아 학창시절 논란은 없을 것으로 판단 됨.","럭셔리",R.drawable.ic_goyoonjung),
        Model("고윤정4","배우", listOf("Keyword1", "Keyword2"), 4, 100,5,"성형논란이 있었으나 악의적 편집으로 밝혀짐. 이 사건 때 동창들이 나서서 변호를 해주는 것으로 보아 학창시절 논란은 없을 것으로 판단 됨.","럭셔리",R.drawable.ic_goyoonjung),
        Model("고윤정5","배우", listOf("Keyword1", "Keyword2"), 1, 98,2,"성형논란이 있었으나 악의적 편집으로 밝혀짐. 이 사건 때 동창들이 나서서 변호를 해주는 것으로 보아 학창시절 논란은 없을 것으로 판단 됨.","럭셔리",R.drawable.ic_goyoonjung),
    )

    val similardummyList = listOf(
        mapOf(
            "이름" to "고윤정",
            "직업" to "모델",
            "이미지" to R.drawable.ic_goyoonjung
        ),
        mapOf(
            "이름" to "고윤정2",
            "직업" to "모델",
            "이미지" to R.drawable.ic_goyoonjung
        ),
        mapOf(
            "이름" to "고윤정3",
            "직업" to "모델",
            "이미지" to R.drawable.ic_goyoonjung
        ),
        mapOf(
            "이름" to "고윤정4",
            "직업" to "모델",
            "이미지" to R.drawable.ic_goyoonjung
        ),
        mapOf(
            "이름" to "고윤정5",
            "직업" to "모델",
            "이미지" to R.drawable.ic_goyoonjung
        ),
        mapOf(
            "이름" to "고윤정6",
            "직업" to "모델",
            "이미지" to R.drawable.ic_goyoonjung
        ),
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        _binding = FragmentOutputStep1Binding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val similarModelAdapter = SimilarModelAdapter(similardummyList)
        binding.outputStep1SimilarModelView.adapter = similarModelAdapter

        setupSpinner()
        initializeViewPagerWithDummyData()
        setBackButton()
    }


    private fun initializeViewPagerWithDummyData() {
        val initialGroupedItems = dummyList.chunked(3)
        var groupedItemsAdapter = GroupedItemsAdapter(initialGroupedItems)
        binding.outputStep1ModelView.adapter = groupedItemsAdapter

        TabLayoutMediator(binding.outputStep1TabDots, binding.outputStep1ModelView) { tab, position ->

        }.attach()
    }


    private fun sortAndGroupListBy(criteria: String) {
        val sortedList = when (criteria) {
            "적합도순" -> dummyList.sortedBy { it.rank }
            "광고비순" -> dummyList.sortedBy { it.relevance }
            "화제성순" -> dummyList.sortedBy { it.hotness }
            else -> dummyList
        }

        val newgroupedItems = sortedList.chunked(3)
        updateGroupedItemsAdapter(newgroupedItems)
    }

    private fun setupSpinner() {
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.output_filter_options,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.outputFilterSpinner.adapter = adapter
        }

        binding.outputFilterSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                when (position) {
                    0 -> sortAndGroupListBy("적합도순")
                    1 -> sortAndGroupListBy("화제성순")
                    2 -> sortAndGroupListBy("광고비순")
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }


    private fun updateGroupedItemsAdapter(newgroupedItems: List<List<Model>>) {
        val groupedItemsAdapter = GroupedItemsAdapter(newgroupedItems)
        groupedItemsAdapter.updateData(newgroupedItems)
        binding.outputStep1ModelView.adapter = groupedItemsAdapter
    }

    private fun setBackButton() {
        binding.outputStep1Toolbar.setNavigationOnClickListener {
            val navController = findNavController()
            navController.navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}