package taufiq.apps.gsuapp.adapter.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import taufiq.apps.gsuapp.views.fragment.FollowersFragment
import taufiq.apps.gsuapp.views.fragment.FollowingFragment

/**
 * Created By Taufiq on 3/16/2021.
 *
 */

class PagerAdapter(activity: AppCompatActivity, data: Bundle) : FragmentStateAdapter(activity) {
    private var bundles: Bundle = data

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FollowersFragment()
            1 -> fragment = FollowingFragment()
        }
        fragment?.arguments = this.bundles
        return fragment as Fragment
    }

    override fun getItemCount() = 2
}