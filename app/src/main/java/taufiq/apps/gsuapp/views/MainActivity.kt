package taufiq.apps.gsuapp.views

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.viewbinding.library.activity.viewBinding
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import taufiq.apps.gsuapp.R
import taufiq.apps.gsuapp.adapter.main.UserGithubListAdapter
import taufiq.apps.gsuapp.databinding.ActivityMainBinding
import taufiq.apps.gsuapp.viewmodel.MainViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewBinding<ActivityMainBinding>()
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = ""

        binding.etSearch.apply {
            doOnTextChanged { text, _, _, _ -> mainViewModel.getSearchUser(text.toString()) }
            doAfterTextChanged { query -> mainViewModel.getSearchUser(query.toString()) }
//            doBeforeTextChanged { text, _, _, _ -> mainViewModel.getSearchUser(text.toString()) }
        }


        mainViewModel.dataSearchUser.observe(this) { data ->
            binding.rvList.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = UserGithubListAdapter(arrayListOf()) { items ->
                    // on click list item
                    startActivity(Intent(this@MainActivity, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.DETAIL_KEY, items)
                        putExtra(DetailActivity.DETAIL_ID, items.id)
                    })
                }.also {
                    it.setData(data.items)
                    if (it.itemCount == -1) {
                        binding.rvList.visibility = View.GONE
                        binding.lottieState.visibility = View.VISIBLE
                    } else {
                        binding.rvList.visibility = View.VISIBLE
                        binding.lottieState.visibility = View.GONE
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_favorite -> {
                startActivity(Intent(this, FavoriteActivity::class.java))
            }

            R.id.menu_setting -> {
                startActivity(Intent(this, SettingsActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}