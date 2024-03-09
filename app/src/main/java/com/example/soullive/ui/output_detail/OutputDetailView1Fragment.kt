package com.example.soullive.ui.output_detail

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.soullive.R
import com.example.soullive.databinding.FragmentOuputDetailView1Binding

class OutputDetailView1Fragment : Fragment() {
    private var _binding: FragmentOuputDetailView1Binding? = null
    private val binding get() = _binding!!

    private var isDropdownExpandedTopic: Boolean = false
    private var isDropdownExpandedIssue: Boolean = false
    private var isDropdownExpandedStrategy: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOuputDetailView1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivDetailTopicArrow.setOnClickListener {
            toggleDropdown(binding.tvDetailTopicDropdown, binding.ivDetailModelTopicBox, isDropdownExpandedTopic)
            isDropdownExpandedTopic = !isDropdownExpandedTopic
            updateArrowIcon(binding.ivDetailTopicArrow, isDropdownExpandedTopic)
        }

        binding.ivDetailIssueArrow.setOnClickListener {
            toggleDropdown(binding.tvDetailIssueDropdown, binding.ivDetailModelIssueBox, isDropdownExpandedIssue)
            isDropdownExpandedIssue = !isDropdownExpandedIssue
            updateArrowIcon(binding.ivDetailIssueArrow, isDropdownExpandedIssue)
        }

        binding.ivDetailStrategyArrow.setOnClickListener {
            toggleDropdown(binding.tvDetailStrategyDropdown, binding.ivDetailModelStrategyBox, isDropdownExpandedStrategy)
            isDropdownExpandedStrategy = !isDropdownExpandedStrategy
            updateArrowIcon(binding.ivDetailStrategyArrow, isDropdownExpandedStrategy)
        }
    }

    private fun toggleDropdown(contentView: TextView, backgroundView: ImageView, isExpanded: Boolean) {
        val startHeight = if (isExpanded) {
            backgroundView.height
        } else {
            backgroundView.layoutParams.height
        }

        val endHeight = if (isExpanded) {
            resources.getDimensionPixelSize(R.dimen.original_background_height)
        } else {
            contentView.measure(
                View.MeasureSpec.makeMeasureSpec(backgroundView.width, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            )
            contentView.measuredHeight + resources.getDimensionPixelSize(R.dimen.original_background_height)
        }

        val valueAnimator = ValueAnimator.ofInt(startHeight, endHeight)
        valueAnimator.addUpdateListener { animation ->
            val layoutParams = backgroundView.layoutParams
            layoutParams.height = animation.animatedValue as Int
            backgroundView.layoutParams = layoutParams
        }

        valueAnimator.duration = 300
        valueAnimator.start()

        if (isExpanded) {
            contentView.visibility = View.GONE
        } else {
            contentView.visibility = View.VISIBLE
        }
    }

    private fun updateArrowIcon(arrowView: ImageView, isExpanded: Boolean) {
        arrowView.setImageResource(if (isExpanded) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
