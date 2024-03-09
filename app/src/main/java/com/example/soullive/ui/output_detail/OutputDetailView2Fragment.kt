package com.example.soullive.ui.output_detail

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.soullive.databinding.FragmentOutputDetailView2Binding

class OutputDetailView2Fragment : Fragment(){
    private var _binding: FragmentOutputDetailView2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentOutputDetailView2Binding.inflate(inflater, container, false)
        return binding.root
    }
}