import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.soullive.R

class SelectedItemsAdapter : RecyclerView.Adapter<SelectedItemsAdapter.ViewHolder>() {
    private var selectedItems: MutableList<String> = mutableListOf()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.search_view_selected_item)
    }

    fun addItem(item: String) {
        if (!selectedItems.contains(item)) {
            selectedItems.add(item)
            notifyDataSetChanged()
        }
    }

    fun removeItem(item: String) {
        val index = selectedItems.indexOf(item)
        if (index != -1) {
            selectedItems.removeAt(index)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search_result, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = selectedItems[position]
        holder.textView.text = item
    }

    override fun getItemCount(): Int = selectedItems.size
}
