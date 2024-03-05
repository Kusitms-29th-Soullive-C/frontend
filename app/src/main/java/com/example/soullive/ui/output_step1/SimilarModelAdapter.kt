package com.example.soullive.ui.output_step1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.soullive.R

class SimilarModelAdapter(private val modelList: List<Map<String, Any>>) : RecyclerView.Adapter<SimilarModelAdapter.ModelViewHolder>() {
    class ModelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var modelName: TextView = view.findViewById(R.id.similar_model_name)
        var modelJob: TextView = view.findViewById(R.id.similar_model_job)
        var modelImage: ImageView = view.findViewById(R.id.similar_model_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_output_step1_similar, parent, false)
        return ModelViewHolder(view)
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        val item = modelList[position]
        holder.modelName.text = item["이름"] as String
        holder.modelJob.text = item["직업"] as String
        holder.modelImage.setImageResource(item["이미지"] as Int)
    }

    override fun getItemCount(): Int {
        return modelList.size
    }
}