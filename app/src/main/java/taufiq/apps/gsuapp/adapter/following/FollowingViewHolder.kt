package taufiq.apps.gsuapp.adapter.following

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import taufiq.apps.gsuapp.data.remote.responses.following.FollowingResponse
import taufiq.apps.gsuapp.databinding.ItemFollowRowBinding

/**
 * Created By Taufiq on 3/12/2021.
 *
 */
class FollowingViewHolder(private val binding: ItemFollowRowBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindList(data: FollowingResponse, listener: (FollowingResponse) -> Unit) {
        binding.apply {
            ivFollower.load(data.avatarUrl) {
                transformations(CircleCropTransformation())
            }
            tvUsernameFollower.text = data.login
            root.setOnClickListener { listener(data) }
        }

    }
}