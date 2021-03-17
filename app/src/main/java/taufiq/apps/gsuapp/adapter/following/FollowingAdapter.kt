package taufiq.apps.gsuapp.adapter.following

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import taufiq.apps.gsuapp.data.remote.responses.following.FollowingResponse
import taufiq.apps.gsuapp.databinding.ItemFollowRowBinding

/**
 * Created By Taufiq on 3/17/2021.
 *
 */
class FollowingAdapter(
    val item: ArrayList<FollowingResponse>,
    private val listener: (FollowingResponse) -> Unit
) : RecyclerView.Adapter<FollowingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingViewHolder =
        FollowingViewHolder(
            ItemFollowRowBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: FollowingViewHolder, position: Int) =
        holder.bindList(item[position], listener)

    override fun getItemCount(): Int = item.size

    fun setData(items: List<FollowingResponse>) {
        item.apply {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }
}