package taufiq.apps.gsuapp.adapter.favorit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import taufiq.apps.gsuapp.data.local.FavoriteEntity
import taufiq.apps.gsuapp.databinding.ItemFavoriteRowBinding

/**
 * Created By Taufiq on 3/17/2021.
 *
 */
class FavoriteAdapter(
    val item: ArrayList<FavoriteEntity>,
    private val listener: (FavoriteEntity) -> Unit
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

    fun setData(items: List<FavoriteEntity>) {
        item.apply {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }
}