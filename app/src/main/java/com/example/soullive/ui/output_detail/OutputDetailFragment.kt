package com.example.soullive.ui.output_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.example.soullive.databinding.FragmentOutputDetailBinding

class OutputDetailFragment : Fragment() {
    private var _binding: FragmentOutputDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOutputDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = 4

            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> OutputDetailView1Fragment()
                    1 -> OutputDetailView2Fragment()
                    2 -> OutputDetailView3Fragment()
                    else -> OutputDetailView4Fragment()
                }
            }
        }
        binding.viewpager.adapter = adapter

        TabLayoutMediator(binding.tlOutputDetail, binding.viewpager) { tab, position ->
            tab.text = when (position) {
                0 -> "전체보기"
                1 -> "브랜드평판"
                2 -> "전략방향"
                else -> "광고전적"
            }
        }.attach()

        binding.ivDetailBookmark.setOnClickListener {
            it.isSelected = !it.isSelected
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
