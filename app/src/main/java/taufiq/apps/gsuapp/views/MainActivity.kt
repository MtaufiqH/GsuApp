package taufiq.apps.gsuapp.views

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.viewbinding.library.activity.viewBinding
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import taufiq.apps.gsuapp.R
import taufiq.apps.gsuapp.adapter.main.UserGithubListAdapter
import taufiq.apps.gsuapp.databinding.ActivityMainBinding
import taufiq.apps.gsuapp.viewmodel.MainViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding()
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.title = ""

        mainViewModel.dataSearchUser.observe(this) { data ->
            binding.rvList.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = UserGithubListAdapter(arrayListOf()) { items ->
                    startActivity(Intent(this@MainActivity, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.DETAIL_KEY, items.login)
                        putExtra(DetailActivity.DETAIL_ID, items.id)
                        putExtra(DetailActivity.DETAIL_AVATAR, items.avatarUrl)
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchManager = getSystemService(SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search_menu).actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_github_user_here)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                mainViewModel.getSearchUser(query.toString())
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?) = false
        })
        return true
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