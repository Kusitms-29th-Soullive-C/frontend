package com.example.soullive.ui.output_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.soullive.R
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
        setViewPagerTabs()
        setBookmarkClick()
        setDownloadClick()
        setBackButton()
    }

    private fun setBackButton() {
        binding.outputDetailToolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_outputDetail_to_outputStep1)
        }
    }

    private fun setViewPagerTabs() {
        val adapter = getViewPagerAdapter()
        binding.viewpager.adapter = adapter
        setupTabLayout()
    }

    private fun getViewPagerAdapter(): FragmentStateAdapter {
        return object : FragmentStateAdapter(this) {
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
    }

    private fun setupTabLayout() {
        TabLayoutMediator(binding.tlOutputDetail, binding.viewpager) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()
    }

    private fun getTabTitle(position: Int): String {
        return when (position) {
            0 -> "전체보기"
            1 -> "브랜드평판"
            2 -> "전략방향"
            else -> "광고전적"
        }
    }

    private fun setBookmarkClick() {
        binding.ivDetailBookmark.setOnClickListener {
            it.isSelected = !it.isSelected
        }
    }

    private fun setDownloadClick() {
        binding.ivDownload.setOnClickListener {
            DownloadDialogFragment().show(childFragmentManager, "downloadDialog")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}