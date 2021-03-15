package taufiq.apps.gsuapp.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import taufiq.apps.gsuapp.data.remote.responses.search.Item
import taufiq.apps.gsuapp.databinding.ItemUserRowBinding

/**
 * Created By Taufiq on 3/12/2021.
 *
 */
class UserGithubListViewHolder(private val binding: ItemUserRowBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindList(data: Item, listener: (Item) -> Unit) {
        binding.tvUsername.text = data.login
        binding.ivUser.load(data.avatarUrl) {
            this.transformations(CircleCropTransformation())
        }
        binding.root.setOnClickListener { listener(data) }
    }
}