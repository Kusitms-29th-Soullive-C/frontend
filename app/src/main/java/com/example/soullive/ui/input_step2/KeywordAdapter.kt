package com.example.soullive.ui.input_step2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.soullive.R
import com.example.soullive.ui.input_step2.KeywordModel.Companion.KeywordList

class KeywordAdapter(
    private val keywordList: MutableList<KeywordModel>, private val fragment: InputStep2Fragment
) : RecyclerView.Adapter<KeywordAdapter.KeywordViewHolder>() {
    inner class KeywordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(keyword: KeywordModel) {
            itemView.findViewById<TextView>(R.id.tv_keyword).text = keyword.keyword
            val deleteButton = itemView.findViewById<ImageButton>(R.id.btn_delete)
            deleteButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    keywordList.removeAt(position)
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position, keywordList.size)
                    if (keywordList.isEmpty()) {
                        fragment.onKeywordDeleted()
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int = keywordList.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeywordViewHolder {
        return KeywordViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_recyclerview_list_keyword, parent, false)
        )
    }

    override fun onBindViewHolder(holder: KeywordViewHolder, position: Int) {
        holder.bind(KeywordList[position])
    }

}