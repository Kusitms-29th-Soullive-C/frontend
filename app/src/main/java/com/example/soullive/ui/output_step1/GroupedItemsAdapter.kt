package com.example.soullive.ui.output_step1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.soullive.R

class GroupedItemsAdapter(private var groupedItems: List<List<Model>>) : RecyclerView.Adapter<GroupedItemsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemsRecyclerView: RecyclerView = view.findViewById(R.id.output_step1_modelGroup)
    }

    fun updateData(newGroupedItems: List<List<Model>>) {
        this.groupedItems = newGroupedItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.page_models_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val startRanks = mutableListOf<Int>()
        var currentRank = 1
        for (group in groupedItems) {
            startRanks.add(currentRank)
            currentRank += group.size
        }
        with(holder.itemsRecyclerView) {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = OutputModelAdapter(groupedItems[position], startRanks[position])
        }
    }

    override fun getItemCount(): Int = groupedItems.size
}
