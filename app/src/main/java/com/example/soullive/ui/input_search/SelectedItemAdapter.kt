import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.soullive.R

class SelectedItemsAdapter : RecyclerView.Adapter<SelectedItemsAdapter.ViewHolder>() {
    private var items: MutableList<Map<String, Any>> = mutableListOf()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val textView: TextView = view.findViewById(R.id.search_view_selected_item)
        val textViewjob : TextView = view.findViewById(R.id.search_view_result_job)

    }

    fun addItem(item: Map<String, Any>) {
        val itemName = item["이름"] as? String ?: return
        if (!items.any { (it["이름"] as? String ?: "") == itemName }) {
            items.add(item)
            notifyDataSetChanged()
        }
    }

    fun removeItem(item: Map<String, Any>) {
        val itemName = item["이름"] as? String ?: return
        val index = items.indexOfFirst { (it["이름"] as? String ?: "") == itemName }
        if (index != -1) {
            items.removeAt(index)
            notifyDataSetChanged()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search_result, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.textView.text = item["이름"] as? String
        holder.textViewjob.text = item["직업"] as? String
    }

    override fun getItemCount(): Int = items.size
}
