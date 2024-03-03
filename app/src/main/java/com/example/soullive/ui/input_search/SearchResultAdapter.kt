import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.soullive.R

class SearchResultAdapter(private val onItemClicked: (Map<String, Any>, Boolean) -> Unit) : RecyclerView.Adapter<SearchResultAdapter.ViewHolder>() {
    private var items: List<Map<String, Any>> = emptyList()
    private var selectedItems: MutableList<Map<String, Any>> = mutableListOf()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.search_selected_name)
        val textJobView: TextView = view.findViewById(R.id.search_selected_job)
    }

    fun setData(newData: List<Map<String, Any>>) {
        items = newData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search_result, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        val itemName = item["이름"] as? String ?: ""
        val jobName = item["직업"] as? String ?: ""
        holder.textView.text = itemName
        holder.textJobView.text = jobName


        holder.itemView.isSelected = selectedItems.any { it["이름"] == itemName }

        holder.itemView.setOnClickListener {
            val isSelected = selectedItems.any { it["이름"] == itemName }
            if (isSelected) {
                selectedItems.removeAll { it["이름"] == itemName }
                onItemClicked(item, false)
            } else {
                selectedItems.add(item)
                onItemClicked(item, true)
            }
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int = items.size
}
