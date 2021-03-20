package taufiq.apps.consumerapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import taufiq.apps.consumerapp.databinding.ItemFavoriteRowBinding

/**
 * Created By Taufiq on 3/17/2021.
 *
 */
class FavoriteAdapter(
    private val item: ArrayList<FavoriteUser>,
    private val listener: (FavoriteUser) -> Unit
) :
    RecyclerView.Adapter<FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            ItemFavoriteRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindList(item[position], listener)
    }

    override fun getItemCount(): Int = item.size

    fun setData(items: List<FavoriteUser>) {
        item.apply {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }
}