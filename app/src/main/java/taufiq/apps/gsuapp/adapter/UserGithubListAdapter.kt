package taufiq.apps.gsuapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import taufiq.apps.gsuapp.databinding.ItemUserRowBinding

/**
 * Created By Taufiq on 3/12/2021.
 *
 */
class UserGithubListAdapter(val context: Context) : RecyclerView.Adapter<UserGithubListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserGithubListViewHolder =
        UserGithubListViewHolder(ItemUserRowBinding.inflate(LayoutInflater.from(context)))

    override fun onBindViewHolder(holder: UserGithubListViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}