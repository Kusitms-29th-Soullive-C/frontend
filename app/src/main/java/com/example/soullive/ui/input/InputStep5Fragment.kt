package com.example.soullive.ui.input

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.soullive.R
import com.example.soullive.databinding.FragmentInputStep5Binding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class InputStep5Fragment : Fragment() {
    private var _binding: FragmentInputStep5Binding? = null
    private val binding get() = _binding!!
    private var job: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentInputStep5Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBackButton()
        setProgressBar()
        setImgLoading()
        confirmButtonEnable()
        setCancleButton()
    }

    private fun setBackButton() {
        binding.inputStep5Toolbar.setNavigationOnClickListener {
            val navController = findNavController()
            navController.navigateUp()
        }
    }

    private fun setProgressBar() {
        binding.inputStep5Progress.progress = 400
    }

    private fun setImgLoading() {
        Glide.with(this)
            .load(R.raw.ic_loading)
            .into(binding.ivLoading)
    }

    private fun confirmButtonEnable() {
        binding.btnInputStep5.isEnabled = false
        job = CoroutineScope(Dispatchers.Main).launch {
            delay(4500)
            binding.btnInputStep5.isEnabled = true
            confirmButton()
        }
    }

    private fun confirmButton() {

    }

    private fun setCancleButton() {
        binding.inputStep5Cancle.setOnClickListener {
            findNavController().navigate(R.id.action_inputStep5Fragment_to_navigation_home)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}