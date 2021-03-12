package taufiq.apps.gsuapp.views

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.viewbinding.library.activity.viewBinding
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import taufiq.apps.gsuapp.R
import taufiq.apps.gsuapp.adapter.UserGithubListAdapter
import taufiq.apps.gsuapp.databinding.ActivityMainBinding
import taufiq.apps.gsuapp.utils.Status
import taufiq.apps.gsuapp.viewmodel.MainViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewBinding<ActivityMainBinding>()
    private val viewmodel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = ""

        binding.idBtn.setOnClickListener {
            val query = binding.etSearchUser.text.toString()
            viewmodel.getSearchUser(query)
        }


        viewmodel.dataSearchUser.observe(this) { data ->
            when (data.status) {
                Status.SUCCESS -> {
                    data.data.let { res ->
                        binding.rvUser.layoutManager = LinearLayoutManager(this)
                        binding.rvUser.adapter = UserGithubListAdapter(this).apply {
                            res?.items?.let { item ->
                                this.setItem(item)
                            }
                        }
                    }
                }

                Status.LOADING -> {
                    binding.pbLoading.visibility = View.VISIBLE
                    binding.rvUser.visibility = View.GONE
                }

                Status.ERROR -> {
                    binding.pbLoading.visibility = View.GONE
                    binding.rvUser.visibility = View.VISIBLE
                    Snackbar.make(binding.rootView, "Something wrong!", Snackbar.LENGTH_LONG).show()
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