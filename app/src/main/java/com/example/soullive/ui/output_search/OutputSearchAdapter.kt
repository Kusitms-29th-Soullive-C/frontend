package com.example.soullive.ui.output_search

import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.soullive.R

class OutputSearchAdapter(private var modelList: List<Map<String, Any>>) :
    RecyclerView.Adapter<OutputSearchAdapter.ModelViewHolder>() {
    class ModelViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var modelName: TextView = view.findViewById(R.id.model_search_name)
        var modelJob: TextView = view.findViewById(R.id.model_search_job)
        val modelImageView: ImageView = view.findViewById(R.id.model_search_image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_search_model, parent, false)
        return ModelViewHolder(view)
    }


    private fun setupSearchView() {

    }

    fun updateData(newItems: List<Map<String, Any>>) {
        this.modelList = newItems
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        val item = modelList[position]

        holder.modelName.text = item["이름"] as String
        holder.modelJob.text = item["직업"] as String

        val imageResId = item["이미지"] as Int
        holder.modelImageView.setImageResource(imageResId)
    }

    override fun getItemCount(): Int {
        return modelList.size
    }
}