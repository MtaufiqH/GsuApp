package taufiq.apps.gsuapp.views.fragment

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.fragment.app.Fragment
import taufiq.apps.gsuapp.R
import taufiq.apps.gsuapp.databinding.FollowersFragmentLayoutBinding

/**
 * Created By Taufiq on 3/16/2021.
 *
 */
class FollowersFragment : Fragment(R.layout.followers_fragment_layout) {

    private val binding by viewBinding<FollowersFragmentLayoutBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnFollowers.setOnClickListener {
            Toast.makeText(
                view.context,
                "Follower fragment here!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}