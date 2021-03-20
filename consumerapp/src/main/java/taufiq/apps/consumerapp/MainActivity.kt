package taufiq.apps.consumerapp

import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import taufiq.apps.consumerapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by viewBinding<ActivityMainBinding>()
    private val viewmodel by viewModels<FavoriteViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewmodel.allFavoriteUser.observe(this) { data ->
            binding.rvUserFavoriteProvider.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = FavoriteAdapter(arrayListOf()).also {
                    it.setData(data)
                }
            }
        }
    }
}