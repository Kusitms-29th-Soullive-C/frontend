package com.example.soullive.ui.input_step2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.soullive.R
import com.example.soullive.ui.input_step2.KeywordModel.Companion.KeywordList

data class KeywordModel(
    val keyword: String,
){
    companion object{
        val KeywordList = listOf(
            KeywordModel("프리미엄"),
            KeywordModel("프리미엄라인인 S시리즈 강조"),
            KeywordModel("새로 들어간 AI "),
            KeywordModel("새로 들어간 AI통역 기능 각인"),
        )
    }
}

class KeywordAdapter(private val keywordList: List<KeywordModel>) :
    RecyclerView.Adapter<KeywordAdapter.KeywordViewHolder>() {
    inner class KeywordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(keyword: KeywordModel) {
            itemView.findViewById<TextView>(R.id.tv_keyword).text=keyword.keyword
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