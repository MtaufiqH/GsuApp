package taufiq.apps.gsuapp.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.viewbinding.library.activity.viewBinding
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import taufiq.apps.gsuapp.adapter.favorit.FavoriteAdapter
import taufiq.apps.gsuapp.databinding.ActivityFavoriteBinding
import taufiq.apps.gsuapp.viewmodel.FavoriteViewModel

@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity() {
    val binding by viewBinding<ActivityFavoriteBinding>()
    private val favViewModel by viewModels<FavoriteViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        favViewModel.allFavoriteUser.observe(this) { data ->
            binding.rvFavorite.apply {
                layoutManager = LinearLayoutManager(this@FavoriteActivity)
                adapter = FavoriteAdapter(arrayListOf()) { favsData ->
                    startActivity(Intent(this@FavoriteActivity, DetailActivity::class.java).also {
                        it.putExtra(DetailActivity.DETAIL_KEY, favsData.username)
                        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    })
                }.also {
                    it.setData(data)
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

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}