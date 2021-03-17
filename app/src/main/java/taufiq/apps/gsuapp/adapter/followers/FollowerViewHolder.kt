package taufiq.apps.gsuapp.adapter.followers

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import taufiq.apps.gsuapp.data.remote.responses.follower.FollowersResponse
import taufiq.apps.gsuapp.databinding.ItemFollowRowBinding

/**
 * Created By Taufiq on 3/12/2021.
 *
 */
class FollowerViewHolder(private val binding: ItemFollowRowBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindList(data: FollowersResponse, listener: (FollowersResponse) -> Unit) {
        binding.apply {
            ivFollower.load(data.avatarUrl) {
                transformations(CircleCropTransformation())
            }
            tvUsernameFollower.text = data.login
            root.setOnClickListener { listener(data) }
        }
    }
}