package taufiq.apps.gsuapp.views

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.viewbinding.library.activity.viewBinding
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import taufiq.apps.gsuapp.R
import taufiq.apps.gsuapp.adapter.UserGithubListAdapter
import taufiq.apps.gsuapp.databinding.ActivityMainBinding
import taufiq.apps.gsuapp.viewmodel.MainViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewBinding<ActivityMainBinding>()
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.title = ""

        binding.etSearch.doAfterTextChanged { query ->
            mainViewModel.getSearchUser(query.toString())
        }

        mainViewModel.dataSearchUser.observe(this) { data ->
            binding.rvList.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = UserGithubListAdapter(arrayListOf()) { items ->
                    Toast.makeText(
                        this@MainActivity,
                        "you're clicked ${items.login}",
                        Toast.LENGTH_SHORT
                    ).show()
                }.also {
                    it.setData(data.items)
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
                Toast.makeText(this, "to the fav page", Toast.LENGTH_SHORT).show()
            }

            R.id.menu_setting -> {
                Toast.makeText(this, "to the setting page", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}