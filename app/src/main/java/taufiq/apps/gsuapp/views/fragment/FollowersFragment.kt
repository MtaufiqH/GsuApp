package taufiq.apps.gsuapp.views.fragment

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import taufiq.apps.gsuapp.R
import taufiq.apps.gsuapp.adapter.followers.FollowerAdapter
import taufiq.apps.gsuapp.databinding.FollowersFragmentLayoutBinding
import taufiq.apps.gsuapp.viewmodel.DetailViewModel
import taufiq.apps.gsuapp.views.DetailActivity

/**
 * Created By Taufiq on 3/16/2021.
 *
 */
class FollowersFragment : Fragment(R.layout.followers_fragment_layout) {

    private val binding by viewBinding<FollowersFragmentLayoutBinding>()
    private val followersViewModel by activityViewModels<DetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val detail = activity as DetailActivity
        val username = detail.getUsername()
        followersViewModel.getFollowers(username)

        followersViewModel.dataFollowers.observe(viewLifecycleOwner) { followers ->
            if (followers != null) {
                binding.rvFollowers.apply {
                    layoutManager =
                        LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
                    adapter = FollowerAdapter(arrayListOf()) {
                        Toast.makeText(
                            context,
                            "you're clicked ${it.login}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }.also { adapter ->
                        adapter.setData(followers)
                        val item = adapter.itemCount
                        if (item == 0 || item < -1) {
                            binding.rvFollowers.visibility = View.INVISIBLE
                            binding.lottieStateFollowers.visibility = View.VISIBLE
                        } else
                            binding.rvFollowers.visibility = View.VISIBLE
                        binding.lottieStateFollowers.visibility = View.GONE
                    }
                }
            }
        }
    }
}



