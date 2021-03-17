package taufiq.apps.gsuapp.adapter.favorit

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import taufiq.apps.gsuapp.data.local.FavoriteEntity
import taufiq.apps.gsuapp.databinding.ItemFavoriteRowBinding

/**
 * Created By Taufiq on 3/17/2021.
 *
 */
class FavoriteViewHolder(val binding: ItemFavoriteRowBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindList(favoriteEntity: FavoriteEntity, listener: (FavoriteEntity) -> Unit) {
        binding.tvNameFav.text = favoriteEntity.name
        binding.tvUsernameFav.text = favoriteEntity.userName
        binding.ivUserFav.load(favoriteEntity.avatarUrl) {
            transformations(CircleCropTransformation())
        }
        binding.rootView.setOnClickListener {
            listener(favoriteEntity)
        }
    }
}