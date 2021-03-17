package taufiq.apps.gsuapp.views

import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import androidx.appcompat.app.AppCompatActivity
import taufiq.apps.gsuapp.R
import taufiq.apps.gsuapp.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {
    val binding by viewBinding<ActivityFavoriteBinding>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
    }
}