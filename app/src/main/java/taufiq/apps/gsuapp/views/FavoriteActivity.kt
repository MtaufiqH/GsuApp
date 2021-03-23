package taufiq.apps.gsuapp.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.viewbinding.library.activity.viewBinding
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import taufiq.apps.gsuapp.R
import taufiq.apps.gsuapp.adapter.favorit.FavoriteAdapter
import taufiq.apps.gsuapp.data.local.FavoriteUser
import taufiq.apps.gsuapp.data.remote.responses.search.Item
import taufiq.apps.gsuapp.databinding.ActivityFavoriteBinding
import taufiq.apps.gsuapp.viewmodel.FavoriteViewModel

@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity() {
    val binding by viewBinding<ActivityFavoriteBinding>()
    private val favViewModel by viewModels<FavoriteViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.favorite_user)

        favViewModel.allFavoriteUser.observe(this) { data ->
            if (data != null) {
                binding.rvFavorite.apply {
                    layoutManager = LinearLayoutManager(this@FavoriteActivity)
                    adapter = FavoriteAdapter(arrayListOf()) { favsData ->
                        startActivity(
                            Intent(
                                this@FavoriteActivity,
                                DetailActivity::class.java
                            ).also {
                                it.putExtra(DetailActivity.DETAIL_KEY, favsData.login)
                                it.putExtra(DetailActivity.DETAIL_ID, favsData.id)
                                it.putExtra(DetailActivity.DETAIL_AVATAR, favsData.avatarUrl)
                            })
                    }.also {
                        val list = mapList(data)
                        it.setData(list)
                        if (it.itemCount == -1 || it.itemCount == 0) {
                            binding.rvFavorite.visibility = View.GONE
                            binding.lottieState.visibility = View.VISIBLE
                        } else {
                            binding.rvFavorite.visibility = View.VISIBLE
                            binding.lottieState.visibility = View.GONE
                        }
                    }
                }
            }

        }
    }

    private fun mapList(data: List<FavoriteUser>): ArrayList<Item> {
        val users = ArrayList<Item>()
        for (user in data) {
            val userMapped = Item(login = user.username, id = user.id, avatarUrl = user.avatarUrl)
            users.add(userMapped)
        }
        return users

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}