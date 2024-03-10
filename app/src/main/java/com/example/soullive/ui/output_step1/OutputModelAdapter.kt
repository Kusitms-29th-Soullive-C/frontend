package com.example.soullive.ui.output_step1

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.soullive.R

class OutputModelAdapter(private val items: List<Model>, private val startRank: Int, private val onGotoDetailClick: (Model) -> Unit) : RecyclerView.Adapter<OutputModelAdapter.ItemViewHolder>() {
    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nameTextView: TextView = view.findViewById(R.id.output_step1_itemName)
        private val jobTextView: TextView = view.findViewById(R.id.output_step1_itemJob)
        private val ModelImageView: ImageView = view.findViewById(R.id.output_step1_imageView)
        val keywordsContainer: LinearLayout = view.findViewById(R.id.output_keywords_container)
        val detailConatiner: LinearLayout = view.findViewById(R.id.output_model_Detail)
        val rankTextView: TextView = view.findViewById(R.id.output_rankTextView)
        val relevanceTextView: TextView = view.findViewById(R.id.output_relevanceTextView)
        val negativeIssue : TextView = view.findViewById(R.id.output_negativeIssuesTextView)
        val hotness : TextView = view.findViewById(R.id.output_hotnessTextView)
        val imageText : TextView = view.findViewById(R.id.output_imageTextView)
        val bookmark: ImageView = view.findViewById(R.id.output_icon_bookmark)
        val ItemRankText: TextView = view.findViewById(R.id.output_item_index)
        val goToDetail : TextView = view.findViewById(R.id.output_gotoDetail)


        fun bind(item: Model) {
            nameTextView.text = item.name
            jobTextView.text = item.job
            rankTextView.text = "${item.rank}순위"
            relevanceTextView.text = "적합도 ${item.relevance}점"
            negativeIssue.text = item.negativeIssues
            hotness.text = "타겟 선호도 ${item.hotness}위"
            imageText.text = item.ImageText
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int, ): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_output_step1_model, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            holder.detailConatiner.visibility = if (holder.detailConatiner.visibility == View.GONE) View.VISIBLE else View.GONE
        }
        val Rank = startRank + position
        holder.ItemRankText.setText(Rank.toString())
        holder.bookmark.setOnClickListener {
            val item = items[holder.adapterPosition]
            item.isBookmarked = !item.isBookmarked
            holder.bookmark.setImageResource(
                if (item.isBookmarked) R.drawable.ic_bookmark_purple else R.drawable.ic_bookmark
            )
        }

        holder.itemView.findViewById<TextView>(R.id.output_gotoDetail)?.setOnClickListener {
            Log.d("자세히보기클릭", "클릭되었음")
            onGotoDetailClick.invoke(items[position])
        }

    }


    override fun getItemCount(): Int = items.size
}