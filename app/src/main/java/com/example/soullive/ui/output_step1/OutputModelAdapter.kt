package com.example.soullive.ui.output_step1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.soullive.R

class OutputModelAdapter (private val items: List<Model>) : RecyclerView.Adapter<OutputModelAdapter.ItemViewHolder>() {
    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nameTextView: TextView = view.findViewById(R.id.output_step1_itemName)
        private val jobTextView: TextView = view.findViewById(R.id.output_step1_itemJob)
        private val ModelImageView : ImageView = view.findViewById(R.id.output_step1_imageView)
        val keywordsContainer: LinearLayout = view.findViewById(R.id.output_keywords_container)
        val detailConatiner : LinearLayout = view.findViewById(R.id.output_model_Detail)
        val rankTextView : TextView = view.findViewById(R.id.output_rankTextView)
        val relevanceTextView : TextView = view.findViewById(R.id.output_relevanceTextView)


        fun bind(item: Model) {
            nameTextView.text = item.name
            jobTextView.text = item.job
            rankTextView.text = "${item.rank}순위"
            relevanceTextView.text = "적합도 ${item.relevance}점"
            ModelImageView.setImageResource(item.imageResId)


            val keywords = item.keywords as? List<String> ?: emptyList()
            keywordsContainer.removeAllViews()

            keywords.forEach { keyword ->
                val keywordView = LayoutInflater.from(itemView.context).inflate(R.layout.item_search_keywords, keywordsContainer, false) as TextView
                keywordView.text = keyword
                keywordsContainer.addView(keywordView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_output_step1_model, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener{
            holder.detailConatiner.visibility =  if (holder.detailConatiner.visibility == View.GONE) View.VISIBLE else View.GONE
        }
    }

    override fun getItemCount(): Int = items.size
}