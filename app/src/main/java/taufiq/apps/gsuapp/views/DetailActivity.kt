package taufiq.apps.gsuapp.views

import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import coil.load
import coil.transform.CircleCropTransformation
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import taufiq.apps.gsuapp.R
import taufiq.apps.gsuapp.adapter.main.PagerAdapter
import taufiq.apps.gsuapp.data.local.FavoriteUser
import taufiq.apps.gsuapp.data.remote.responses.search.Item
import taufiq.apps.gsuapp.databinding.ActivityDetailBinding
import taufiq.apps.gsuapp.utils.ZoomOutPageTransformer
import taufiq.apps.gsuapp.viewmodel.DetailViewModel

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private val binding by viewBinding<ActivityDetailBinding>()
    private val detailViewModel by viewModels<DetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = ""
        val myUsername = intent.getParcelableExtra<Item>(DETAIL_KEY)
        val id = intent.getIntExtra(DETAIL_ID, 0)
        if (myUsername != null) {
            detailViewModel.getDataDetail(myUsername.login)

            var isFavorite = false
            lifecycleScope.launchWhenCreated {
                withContext(Dispatchers.IO) {
                    val userCount = detailViewModel.checkUserFavorite(id)
                    withContext(Dispatchers.Main) {
                        if (userCount > 0) {
                            binding.tbFavorite.isChecked = true
                            isFavorite = true
                        } else {
                            binding.tbFavorite.isChecked = false
                            isFavorite = false
                        }
                    }
                }
            }

            detailViewModel.dataDetail.observe(this) { dataDetail ->
                if (dataDetail != null) {
                    binding.apply {
                        ivProfilDetail.load(dataDetail.avatarUrl) {
                            transformations(CircleCropTransformation())
                            placeholder(R.drawable.ic_user_plcholder)
                        }
                        tvFullName.text = dataDetail.name
                        tvUserName.text = dataDetail.login
                        tvUserLocation.text = dataDetail.location
                        tvFollowerCount.text = dataDetail.followers.toString()
                        tvFollowingCount.text = dataDetail.following.toString()
                    }

                    binding.tbFavorite.setOnClickListener {
                        isFavorite = !isFavorite
                        val name = dataDetail.name
                        if (isFavorite) {

                            val user =
                                FavoriteUser(
                                    myUsername.id,
                                    myUsername.login,
                                    myUsername.avatarUrl,
                                    name
                                )

                            user.let {
                                detailViewModel.insertToFavorite(it)
                            }
                            Toast.makeText(
                                this@DetailActivity,
                                this.getString(R.string.adding_to_fav),
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            val user =
                                FavoriteUser(
                                    myUsername.id,
                                    myUsername.login,
                                    myUsername.avatarUrl,
                                    name
                                )
                            user.let {
                                detailViewModel.deleteFromFavorite(it)
                            }
                            Toast.makeText(
                                this@DetailActivity,
                                this.getString(R.string.delete_from_fav),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        binding.tbFavorite.isChecked = isFavorite
                    }
                }
            }
        }

        val bundle = Bundle().apply {
            putString(DETAIL_KEY, myUsername?.login)
        }

        val pagerAdapter = PagerAdapter(this, bundle)
        binding.viewPagerId.adapter = pagerAdapter
        TabLayoutMediator(binding.tabsId, binding.viewPagerId) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        binding.viewPagerId.setPageTransformer(ZoomOutPageTransformer())
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.followers, R.string.following)
        const val DETAIL_KEY = "detail_key"
        const val DETAIL_ID = "detail_id"
    }
}