package taufiq.apps.gsuapp.views

import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import coil.load
import coil.transform.CircleCropTransformation
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import taufiq.apps.gsuapp.R
import taufiq.apps.gsuapp.adapter.main.PagerAdapter
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
        val myUsername = intent.getStringExtra(DETAIL_KEY)
        val myId = intent.getIntExtra(DETAIL_ID, 0)
        val myAvatar = intent.getStringExtra(DETAIL_AVATAR)
        if (myUsername != null && myAvatar != null) {
            detailViewModel.getDataDetail(myUsername)

            var isFavorite = false
            CoroutineScope(Dispatchers.IO).launch {
                val userCount = detailViewModel.checkUserFavorite(myId)
                withContext(Dispatchers.Main) {
                    if (userCount != null) {
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

            binding.tbFavorite.setOnClickListener {
                isFavorite = !isFavorite
                if (isFavorite) {
                    detailViewModel.insertToFavorite(myUsername, myId, myAvatar)
                    Toast.makeText(this, getString(R.string.adding_to_fav), Toast.LENGTH_SHORT)
                        .show()
                } else {
                    detailViewModel.deleteFromFavorite(myId)
                    Toast.makeText(this, getString(R.string.delete_from_fav), Toast.LENGTH_SHORT)
                        .show()
                }
                binding.tbFavorite.isChecked = isFavorite
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
            }
        }


        val bundle = Bundle().apply {
            putString(DETAIL_KEY, myUsername)
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
        const val DETAIL_AVATAR = "avatar_url"
    }
}