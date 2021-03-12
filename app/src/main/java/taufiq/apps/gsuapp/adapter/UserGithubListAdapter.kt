package taufiq.apps.gsuapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import taufiq.apps.gsuapp.data.remote.responses.search.Item
import taufiq.apps.gsuapp.databinding.ItemUserRowBinding

/**
 * Created By Taufiq on 3/12/2021.
 *
 */
class UserGithubListAdapter(private val context: Context) :
    RecyclerView.Adapter<UserGithubListViewHolder>() {

    private var items = listOf<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserGithubListViewHolder =
        UserGithubListViewHolder(ItemUserRowBinding.inflate(LayoutInflater.from(context)))

    override fun onBindViewHolder(holder: UserGithubListViewHolder, position: Int) {
        holder.bindList(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun setItem(item: List<Item>) {
        this.items = item
        notifyDataSetChanged()
    }

}