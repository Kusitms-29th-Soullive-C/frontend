import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.soullive.R

//class SearchResultAdapter : RecyclerView.Adapter<SearchResultAdapter.ViewHolder>() {
class SearchResultAdapter(private val onItemClicked: (String, Boolean) -> Unit) : RecyclerView.Adapter<SearchResultAdapter.ViewHolder>() {
    private var items: List<String> = emptyList()
    private var selectedItems: MutableList<String> = mutableListOf()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.text_view_result)
    }

    fun setData(newData: List<String>) {
        items = newData
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search_result, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.textView.text = item
        holder.itemView.isSelected = selectedItems.contains(item)

        holder.itemView.setOnClickListener {
            if(selectedItems.contains(item)) {
                selectedItems.remove(item)
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
