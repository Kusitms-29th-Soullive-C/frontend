package com.example.soullive.ui.input

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.soullive.R
import com.example.soullive.databinding.FragmentInputStep5Binding
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class InputStep5Fragment : Fragment() {
    private var _binding: FragmentInputStep5Binding? = null
    private val binding get() = _binding!!
    private val jobs = mutableListOf<Job>()

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
        setCancleButton()
        setProgressBar()
        setImgLoading()
        startBlinkingEffect()
        gotoOutputButton()
    }

    private fun setBackButton() {
        binding.inputStep5Toolbar.setNavigationOnClickListener {
            val navController = findNavController()
            navController.navigateUp()
        }
    }

    private fun setCancleButton() {
        binding.inputStep5Cancle.setOnClickListener {
            findNavController().navigate(R.id.action_inputStep5_to_navigation_home)
        }
    }

    private fun setProgressBar() {
        binding.inputStep5Progress.progress = 400
    }

    private fun setImgLoading() {
        Glide.with(this)
            .asGif()
            .load(R.raw.ic_loading)
            .into(binding.ivLoading)

        lifecycleScope.launch {
            delay(4500)
            showFirstFrameAsStaticImage()
        }
    }

    private fun showFirstFrameAsStaticImage() {
        Glide.with(this)
            .asBitmap()
            .load(R.raw.ic_loading)
            .into(binding.ivLoading)
    }

    private fun startBlinkingEffect() {
        val animator = ValueAnimator.ofFloat(0f, 1f).apply {
            duration = 800
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.REVERSE
            addUpdateListener { animation ->
                binding.tvInputStep5Analyze.alpha = animation.animatedValue as Float
            }
        }
        lifecycleScope.launch {
            animator.start()
            binding.btnInputStep5.isEnabled = false
            delay(4500)
            animator.cancel()
            completeAnalyze()
        }
    }

    private fun completeAnalyze() {
        binding.tvInputStep5Analyze.alpha = 1f
        binding.tvInputStep5Analyze.text = getString(R.string.new_analyze_text)
        binding.tvInputStep5Waiting.text = getString(R.string.new_waiting_text)
        binding.btnInputStep5.isEnabled = true
    }


    private fun gotoOutputButton() {
        binding.btnInputStep5.setOnClickListener {
            findNavController().navigate(R.id.action_inputStep5_to_OutputStep1Fragment)
        }
    }


    override fun onDestroyView() {
        jobs.forEach { it.cancel() }
        super.onDestroyView()
        _binding = null
    }
}