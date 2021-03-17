package taufiq.apps.gsuapp.views.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import taufiq.apps.gsuapp.R
import taufiq.apps.gsuapp.adapter.following.FollowingAdapter
import taufiq.apps.gsuapp.databinding.FollowingFragmentLayoutBinding
import taufiq.apps.gsuapp.viewmodel.DetailViewModel
import taufiq.apps.gsuapp.views.DetailActivity

/**
 * Created By Taufiq on 3/16/2021.
 *
 */
class FollowingFragment : Fragment(R.layout.following_fragment_layout) {

    private val binding by viewBinding<FollowingFragmentLayoutBinding>()
    private val followingViewModel by activityViewModels<DetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        this.arguments?.getString("USERNAME")?.let {
//            followingViewModel.getFollowing(it)
//        }

        val detail = activity as DetailActivity
        val username = detail.getUsername()
        followingViewModel.getFollowing(username)

        binding.apply {
            followingViewModel.dataFollowing.observe(viewLifecycleOwner) { followingData ->
                Log.d("TAG", "data $followingData")
                rvFollowing.apply {
                    layoutManager =
                        LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
                    adapter = FollowingAdapter(arrayListOf()) {
                        Toast.makeText(
                            context,
                            "you're clicked ${it.login}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }.also { adapter ->
                        adapter.setData(followingData)
                        val item = adapter.itemCount
                        if (item <= 0) {
                            rvFollowing.visibility = View.INVISIBLE
                            lottieStateFollowing.visibility = View.VISIBLE
                        } else
                            rvFollowing.visibility = View.VISIBLE
                        lottieStateFollowing.visibility = View.GONE
                    }
                }
            }
        }
    }
}