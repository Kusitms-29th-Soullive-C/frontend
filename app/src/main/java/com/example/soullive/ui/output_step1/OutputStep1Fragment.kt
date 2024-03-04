package com.example.soullive.ui.output_step1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    val ImageText: String
)


class OutputStep1Fragment : Fragment() {

    private var _binding: FragmentOutputStep1Binding? = null
    private val binding get() = _binding!!
    private lateinit var viewPager : ViewPager2
    private lateinit var OutputModelAdapter : OutputModelAdapter

    val dummyList = listOf(
        Model("고윤정","배우", listOf("Keyword1", "Keyword2"), 1, 95,3,"성형논란이 있었으나 악의적 편집으로 밝혀짐. 이 사건 때 동창들이 나서서 변호를 해주는 것으로 보아 학창시절 논란은 없을 것으로 판단 됨.","럭셔리"),
        Model("고윤정2","배우", listOf("Keyword1", "Keyword2"), 1, 95,3,"성형논란이 있었으나 악의적 편집으로 밝혀짐. 이 사건 때 동창들이 나서서 변호를 해주는 것으로 보아 학창시절 논란은 없을 것으로 판단 됨.","럭셔리"),
        Model("고윤정3","배우", listOf("Keyword1", "Keyword2"), 1, 95,3,"성형논란이 있었으나 악의적 편집으로 밝혀짐. 이 사건 때 동창들이 나서서 변호를 해주는 것으로 보아 학창시절 논란은 없을 것으로 판단 됨.","럭셔리"),
        Model("고윤정4","배우", listOf("Keyword1", "Keyword2"), 1, 95,3,"성형논란이 있었으나 악의적 편집으로 밝혀짐. 이 사건 때 동창들이 나서서 변호를 해주는 것으로 보아 학창시절 논란은 없을 것으로 판단 됨.","럭셔리"),
        Model("고윤정5","배우", listOf("Keyword1", "Keyword2"), 1, 95,3,"성형논란이 있었으나 악의적 편집으로 밝혀짐. 이 사건 때 동창들이 나서서 변호를 해주는 것으로 보아 학창시절 논란은 없을 것으로 판단 됨.","럭셔리"),
        Model("고윤정6","배우", listOf("Keyword1", "Keyword2"), 1, 95,3,"성형논란이 있었으나 악의적 편집으로 밝혀짐. 이 사건 때 동창들이 나서서 변호를 해주는 것으로 보아 학창시절 논란은 없을 것으로 판단 됨.","럭셔리"),
        Model("고윤정7","배우", listOf("Keyword1", "Keyword2"), 1, 95,3,"성형논란이 있었으나 악의적 편집으로 밝혀짐. 이 사건 때 동창들이 나서서 변호를 해주는 것으로 보아 학창시절 논란은 없을 것으로 판단 됨.","럭셔리"),

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

        val groupedItems = dummyList.chunked(3)
        val groupedItemsAdapter = GroupedItemsAdapter(groupedItems)
        binding.outputStep1ModelView.adapter = groupedItemsAdapter
//        viewPager = view.findViewById(R.id.output_step1_modelView)
//        OutputModelAdapter = OutputModelAdapter(dummyList)
//        viewPager.adapter = OutputModelAdapter

        TabLayoutMediator(binding.tabDots, binding.outputStep1ModelView) { tab, position ->

        }.attach()
        setBackButton()
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