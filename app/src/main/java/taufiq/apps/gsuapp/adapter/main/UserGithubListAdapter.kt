package taufiq.apps.gsuapp.adapter.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import taufiq.apps.gsuapp.data.remote.responses.search.Item
import taufiq.apps.gsuapp.databinding.ItemUserRowBinding

/**
 * Created By Taufiq on 3/12/2021.
 *
 */
class UserGithubListAdapter(
    private val item: ArrayList<Item>,
    private val listener: (Item) -> Unit
) :
    RecyclerView.Adapter<UserGithubListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserGithubListViewHolder {
        return UserGithubListViewHolder(
            ItemUserRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserGithubListViewHolder, position: Int) {
        holder.bindList(item[position], listener)
    }

    override fun getItemCount(): Int = item.size

    fun setData(items: List<Item>) {
        item.apply {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }
}