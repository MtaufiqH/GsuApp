package taufiq.apps.gsuapp.adapter.favorit

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import taufiq.apps.gsuapp.data.local.FavoriteUser
import taufiq.apps.gsuapp.databinding.ItemFavoriteRowBinding

/**
 * Created By Taufiq on 3/17/2021.
 *
 */
class FavoriteViewHolder(val binding: ItemFavoriteRowBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindList(userFavs: FavoriteUser, listener: (FavoriteUser) -> Unit) {
        binding.tvNameFav.text = userFavs.name
        binding.tvUsernameFav.text = userFavs.username
        binding.ivUserFav.load(userFavs.avatarUrl) {
            transformations(CircleCropTransformation())
        }
        binding.rootView.setOnClickListener {
            listener(userFavs)
        }
    }
}