package taufiq.apps.gsuapp.adapter.favorit

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import taufiq.apps.gsuapp.data.remote.responses.search.Item
import taufiq.apps.gsuapp.databinding.ItemFavoriteRowBinding

/**
 * Created By Taufiq on 3/17/2021.
 *
 */
class FavoriteViewHolder(val binding: ItemFavoriteRowBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindList(userFavs: Item, listener: (Item) -> Unit) {
        binding.tvUsernameFav.text = userFavs.login
        binding.ivUserFav.load(userFavs.avatarUrl) {
            transformations(CircleCropTransformation())
        }
        binding.rootView.setOnClickListener {
            listener.invoke(userFavs)
        }
    }
}