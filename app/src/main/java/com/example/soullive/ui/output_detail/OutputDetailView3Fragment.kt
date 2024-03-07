package com.example.soullive.ui.output_detail

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.soullive.databinding.FragmentOutputDetailView3Binding

class OutputDetailView3Fragment : Fragment(){
    private var _binding: FragmentOutputDetailView3Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentOutputDetailView3Binding.inflate(inflater, container, false)
        return binding.root
    }
}