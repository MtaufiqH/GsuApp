package taufiq.apps.gsuapp.views.fragment

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.fragment.app.Fragment
import taufiq.apps.gsuapp.R
import taufiq.apps.gsuapp.databinding.FollowingFragmentLayoutBinding

/**
 * Created By Taufiq on 3/16/2021.
 *
 */
class FollowingFragment : Fragment(R.layout.following_fragment_layout) {

    private val binding by viewBinding<FollowingFragmentLayoutBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnFollowing.setOnClickListener {
            Toast.makeText(view.context, "Following fragment here!", Toast.LENGTH_SHORT).show()
        }
    }
}