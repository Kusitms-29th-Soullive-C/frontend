package com.example.soullive.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import com.example.soullive.R
import com.example.soullive.databinding.FragmentInputStartBinding


class InputSerachFragment : Fragment() {
    private var _binding: FragmentInputStartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        _binding = FragmentInputStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBackButton()
        animation()
    }

    private fun setBackButton() {
        binding.toolbar.setNavigationOnClickListener {
            val navController = findNavController()
            navController.navigateUp()
        }
    }


    private fun animation(){
        val animation = AnimationUtils.loadAnimation(context, R.anim.move_up_down)
        binding.iv3dLogo.startAnimation(animation)
    }

    private fun startButton(){
        binding.btnStart.setOnClickListener {
            // findNavController().navigate(R.id.)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}