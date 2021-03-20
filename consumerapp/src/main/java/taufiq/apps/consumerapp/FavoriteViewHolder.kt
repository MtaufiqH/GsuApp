package taufiq.apps.consumerapp

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import taufiq.apps.consumerapp.databinding.ItemFavoriteRowBinding

/**
 * Created By Taufiq on 3/17/2021.
 *
 */
class FavoriteViewHolder(private val binding: ItemFavoriteRowBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindList(userFavs: FavoriteUser) {
        binding.tvNameFav.text = userFavs.name
        binding.tvUsernameFav.text = userFavs.username
        binding.ivUserFav.load(userFavs.avatarUrl) {
            transformations(CircleCropTransformation())
        }
    }
}