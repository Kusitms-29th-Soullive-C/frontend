package com.example.soullive.ui.input_step2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.soullive.R
import com.example.soullive.databinding.FragmentInputStep2Binding
import com.google.android.material.internal.ViewUtils.hideKeyboard


class InputStep2Fragment : Fragment() {

    private var _binding: FragmentInputStep2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentInputStep2Binding.inflate(inflater, container, false)
        return inflater.inflate(R.layout.fragment_input_step2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}