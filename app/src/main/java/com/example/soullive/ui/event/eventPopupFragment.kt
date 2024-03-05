package com.example.soullive.ui.event

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.soullive.R
import com.example.soullive.databinding.FragmentEventPopupBinding
import com.example.soullive.databinding.FragmentOnBoardingBinding

class eventPopupFragment : Fragment() {

    private var _binding: FragmentEventPopupBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEventPopupBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        closeButton()
        nextButton()
    }

    private fun closeButton() {
        binding.btnClose.setOnClickListener {
            findNavController().navigate(R.id.action_eventPopup_to_navigation_home)
        }
    }
    private fun nextButton() {
        binding.btnEventPopupNext.setOnClickListener {
            findNavController().navigate(R.id.action_eventPopup_to_subscribe)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}