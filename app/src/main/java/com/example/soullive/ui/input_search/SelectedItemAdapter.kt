import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.soullive.R


class SelectedItemsAdapter(private val onItemClicked: (Map<String, Any>, Boolean) -> Unit) : RecyclerView.Adapter<SelectedItemsAdapter.ViewHolder>() {
    private var items: MutableList<Map<String, Any>> = mutableListOf()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val textView: TextView = view.findViewById(R.id.search_view_selected_item)
        val textViewjob: TextView = view.findViewById(R.id.search_view_selected_job)
        val keywordsContainer: LinearLayout = view.findViewById(R.id.keywords_container)
        val imageView: ImageView = view.findViewById(R.id.search_selected_imageView)
    }

    fun addItem(item: Map<String, Any>, context: Context) {
        val itemName = item["이름"] as? String ?: return
        if (!items.any { (it["이름"] as? String ?: "") == itemName }) {
            items.add(item)
            notifyDataSetChanged()
        }
    }

    fun removeItem(item: Map<String, Any>, context: Context) {
        val itemName = item["이름"] as? String ?: return
        val index = items.indexOfFirst { (it["이름"] as? String ?: "") == itemName }
        if (index != -1) {
            items.removeAt(index)
            notifyDataSetChanged()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search_selected, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.textView.text = item["이름"] as? String
        holder.textViewjob.text = item["직업"] as? String

        val keywords = item["키워드"] as? List<String> ?: emptyList()

        holder.keywordsContainer.removeAllViews()

        keywords.forEach { keyword ->
            val keywordView = LayoutInflater.from(holder.itemView.context).inflate(R.layout.item_search_keywords, holder.keywordsContainer, false) as TextView
            keywordView.text = keyword
            holder.keywordsContainer.addView(keywordView)
        }

        val imageResId = item["이미지"] as? Int
        if (imageResId != null) {
            holder.imageView.setImageResource(imageResId)
        }

        holder.itemView.setOnClickListener {
            onItemClicked(item, false)
            notifyDataSetChanged()
        }

        holder.itemView.setOnClickListener {
            onItemClicked(item, false)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = items.size
}
