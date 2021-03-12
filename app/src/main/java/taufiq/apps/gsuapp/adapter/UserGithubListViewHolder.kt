package taufiq.apps.gsuapp.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import taufiq.apps.gsuapp.data.remote.responses.search.Item
import taufiq.apps.gsuapp.data.remote.responses.search.SearchUserResponse
import taufiq.apps.gsuapp.databinding.ItemUserRowBinding

/**
 * Created By Taufiq on 3/12/2021.
 *
 */
class UserGithubListViewHolder(private val view : ItemUserRowBinding) : RecyclerView.ViewHolder(view.root) {
    fun bindList(data: Item){
        view.tvUsername.text = data.login
        view.ivUser.load(data.avatarUrl)
    }
}