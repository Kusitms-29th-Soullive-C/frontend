package com.example.soullive.ui.fitness

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.soullive.databinding.FragmentFitnessBinding

class FitnessFragment : Fragment() {

    private var _binding: FragmentFitnessBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fitnessViewModel =
            ViewModelProvider(this).get(FitnessViewModel::class.java)

        _binding = FragmentFitnessBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.fitness
        fitnessViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}