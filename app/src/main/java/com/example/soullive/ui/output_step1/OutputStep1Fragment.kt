package com.example.soullive.ui.output_step1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
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
    val imageResId: Int,
    var isBookmarked: Boolean = false,
)

class OutputStep1Fragment : Fragment() {

    private var _binding: FragmentOutputStep1Binding? = null
    private val binding get() = _binding!!

    val dummyList = listOf(
        Model("고윤정", "배우", listOf("스타일리시한", "매력적인", "패션 센스"), 1, 95, 3, "성형논란이 있었으나 악의적 편집으로 밝혀짐. 이 사건 때 동창들이 나서서 변호를 해주는 것으로 보아 학창시절 논란은 없을 것으로 판단 됨.", "럭셔리", R.drawable.ic_goyoonjung),
        Model("잇섭", "유튜버", listOf("정보통", "매력있는", "신뢰도 높은"), 2, 88, 4, "이전에 갤럭시 광고 관련 논란을 빚어 계약 성사가 어려울 듯함", "똑똑한", R.drawable.ic_itsub),
        Model("한소희", "배우", listOf("도도한", "럭셔리한", "호감형의"), 3, 33, 1, "모친 빚투 논란", "예쁨", R.drawable.ic_sohee),
        Model("이선빈", "배우", listOf("다재다능한", "매력적인", "예술적인"), 4, 100, 5, "알려진 바 없음", "럭셔리", R.drawable.ic_output4),
        Model("이재욱", "배우", listOf("잘생긴", "훈훈한"), 5, 98, 2, "최근 카리나와의 열애설 논란","잘생긴", R.drawable.ic_output5),
    )

    val similardummyList = listOf(
        mapOf(
            "이름" to "고윤정",
            "직업" to "모델",
            "적합도" to 9,
            "타겟선호도" to 1,
            "전략" to 2,
            "이미지" to R.drawable.ic_goyoonjung
        ),
        mapOf(
            "이름" to "고윤정2",
            "직업" to "모델",
            "적합도" to 8,
            "타겟선호도" to 5,
            "전략" to 3,
            "이미지" to R.drawable.ic_goyoonjung
        ),
        mapOf(
            "이름" to "고윤정3",
            "직업" to "모델",
            "적합도" to 7,
            "타겟선호도" to 3,
            "전략" to 4,
            "이미지" to R.drawable.ic_goyoonjung
        ),
        mapOf(
            "이름" to "고윤정4",
            "직업" to "모델",
            "적합도" to 6,
            "타겟선호도" to 10,
            "전략" to 5,
            "이미지" to R.drawable.ic_goyoonjung
        ),
        mapOf(
            "이름" to "고윤정5",
            "직업" to "모델",
            "적합도" to 5,
            "타겟선호도" to 11,
            "전략" to 6,
            "이미지" to R.drawable.ic_goyoonjung
        ),
        mapOf(
            "이름" to "고윤정6",
            "직업" to "모델",
            "적합도" to 4,
            "타겟선호도" to 1,
            "전략" to 8,
            "이미지" to R.drawable.ic_goyoonjung
        ),
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentOutputStep1Binding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setModelAdapter()
        setSearchButton()
        setRestartBtn()
        setOutputDialog()
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

    private fun setModelAdapter(){
        val similarModelAdapter = SimilarModelAdapter(similardummyList)
        binding.outputStep1SimilarModelView.adapter = similarModelAdapter
        val similarFilterButtons = listOf(binding.similarBtnFit, binding.similarBtnTarget, binding.similarBtnStrategy)

        binding.similarBtnFit.setOnClickListener {
            updateSelection(it, similarFilterButtons)
            filterAndSortList("적합도")
        }

        binding.similarBtnTarget.setOnClickListener {
            updateSelection(it, similarFilterButtons)
            filterAndSortList("타겟 선호도")
        }

        binding.similarBtnStrategy.setOnClickListener {
            updateSelection(it, similarFilterButtons)
            filterAndSortList("전략")
        }

        binding.outputFilterTextview.setOnClickListener {
            binding.outputFilterSpinner.performClick()
        }
    }

    private fun setRestartBtn(){
        binding.outputBtnRestart.setOnClickListener {
            OutputDialogFragment().show(parentFragmentManager, "outputDialog")
        }

    }

    private fun setOutputDialog(){
        binding.outputBtnRestart.setOnClickListener {
            OutputDialogFragment().show(parentFragmentManager, "outputDialog")
        }
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
                binding.outputFilterTextview.text = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }


    private fun updateGroupedItemsAdapter(newgroupedItems: List<List<Model>>) {
        val groupedItemsAdapter = GroupedItemsAdapter(newgroupedItems)
        groupedItemsAdapter.updateData(newgroupedItems)
        binding.outputStep1ModelView.adapter = groupedItemsAdapter
    }

    private fun filterAndSortList(criteria: String) {
        val sortedList = when (criteria) {
            "적합도" -> similardummyList.sortedByDescending { it["적합도"] as Int }
            "타겟 선호도" -> similardummyList.sortedByDescending { it["타겟선호도"] as Int }
            "전략" -> similardummyList.sortedByDescending { it["전략"] as Int }
            else -> similardummyList
        }
        updateSimilarModelAdapter(sortedList)
    }

    private fun updateSelection(clickedView: View, buttons: List<View>) {
        buttons.forEach { button ->
            button.isSelected = button == clickedView
        }
    }

    private fun updateSimilarModelAdapter(newList: List<Map<String, Any>>) {
        (binding.outputStep1SimilarModelView.adapter as? SimilarModelAdapter)?.updateData(newList)
    }

    private fun setBackButton() {
        binding.outputStep1Toolbar.setNavigationOnClickListener {
            val navController = findNavController()
            navController.navigateUp()
        }
    }

    private fun setSearchButton() {
        binding.outputIcon1.setOnClickListener() {
            findNavController().navigate(R.id.action_outputStep1_to_outputSearch)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}