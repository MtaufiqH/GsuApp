package taufiq.apps.gsuapp.views

import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import coil.load
import coil.transform.CircleCropTransformation
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import taufiq.apps.gsuapp.R
import taufiq.apps.gsuapp.adapter.main.PagerAdapter
import taufiq.apps.gsuapp.data.local.FavoriteEntity
import taufiq.apps.gsuapp.data.remote.responses.search.Item
import taufiq.apps.gsuapp.databinding.ActivityDetailBinding
import taufiq.apps.gsuapp.utils.ZoomOutPageTransformer
import taufiq.apps.gsuapp.viewmodel.DetailViewModel

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private val binding by viewBinding<ActivityDetailBinding>()
    private val detailViewModel by viewModels<DetailViewModel>()
    private lateinit var username: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = ""
        val data = intent.getParcelableExtra<Item>(DETAIL_KEY)
        if (data != null) {
            detailViewModel.getDataDetail(data.login)
            username = data.login

        }

        detailViewModel.dataDetail.observe(this) { dataDetail ->
            dataDetail?.let { data ->
                binding.apply {
                    ivProfilDetail.load(data.avatarUrl) {
                        transformations(CircleCropTransformation())
                    }
                    tvFullName.text = data.name
                    tvUserName.text = data.login
                    tvUserLocation.text = data.location
                    tvFollowerCount.text = data.followers.toString()
                    tvFollowingCount.text = data.following.toString()
                }
            }

            @Suppress("DEPRECATION")
            binding.tbFavorite.apply {
                isChecked = false
                setBackgroundDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_favorite_border
                    )
                )
                setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        binding.tbFavorite.setBackgroundDrawable(
                            ContextCompat.getDrawable(
                                context,
                                R.drawable.ic_favorite
                            )
                        )
                        val user =
                            FavoriteEntity(
                                dataDetail.login,
                                dataDetail.name,
                                dataDetail.avatarUrl,
                                dataDetail.bio.toString(),
                                dataDetail.company,
                                dataDetail.followers,
                                dataDetail.following,
                                dataDetail.location,
                                dataDetail.publicRepos,
                                dataDetail.followersUrl,
                                dataDetail.followingUrl
                            )
                        user.let {
                            detailViewModel.insertToFavorite(it)
                        }
                        Toast.makeText(
                            this@DetailActivity,
                            context.getString(R.string.adding_to_fav),
                            Toast.LENGTH_SHORT
                        ).show()
                    } else
                        binding.tbFavorite.setBackgroundDrawable(
                            ContextCompat.getDrawable(
                                context,
                                R.drawable.ic_favorite_border
                            )
                        )
                    val user =
                        FavoriteEntity(
                            dataDetail.login,
                            dataDetail.name,
                            dataDetail.avatarUrl,
                            dataDetail.bio.toString(),
                            dataDetail.company,
                            dataDetail.followers,
                            dataDetail.following,
                            dataDetail.location,
                            dataDetail.publicRepos,
                            dataDetail.followersUrl,
                            dataDetail.followingUrl
                        )
                    user.let {
                        detailViewModel.deleteFromFavorite(it)
                    }
                    Toast.makeText(
                        this@DetailActivity,
                        context.getString(R.string.delete_from_fav),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        val pagerAdapter = PagerAdapter(this)
        binding.viewPagerId.adapter = pagerAdapter
        TabLayoutMediator(binding.tabsId, binding.viewPagerId) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        binding.viewPagerId.setPageTransformer(ZoomOutPageTransformer())
    }

    fun getUsername() = username

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.followers, R.string.following)
        const val DETAIL_KEY = "detail_key"
    }
}