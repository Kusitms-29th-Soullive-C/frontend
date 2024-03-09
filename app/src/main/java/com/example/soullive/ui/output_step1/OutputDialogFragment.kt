package com.example.soullive.ui.output_step1

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.soullive.R
import com.example.soullive.databinding.FragmentInputStartBinding
import com.example.soullive.databinding.FragmentOutputDialogBinding

class OutputDialogFragment : DialogFragment() {
    private var _binding: FragmentOutputDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentOutputDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            setBackgroundDrawableResource(android.R.color.transparent)
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            setGravity(Gravity.CENTER)

            val context = context
            val metrics = context.resources.displayMetrics
            val screenWidth = metrics.widthPixels
            val marginHorizontal = (screenWidth * 0.1).toInt()

            val params = attributes
            params.width = screenWidth - 2 * marginHorizontal
            attributes = params
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.outputCloseTextView.setOnClickListener {
            dismiss()
        }

        binding.outputNavigateTextView.setOnClickListener {
            findNavController().navigate(R.id.action_outputstep1_to_inputstep1)
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}