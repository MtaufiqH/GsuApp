package taufiq.apps.gsuapp.views

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import taufiq.apps.gsuapp.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupSplashScreen()
    }

    private fun setupSplashScreen() {
        Handler(mainLooper).postDelayed({
            startActivity(Intent(this, MainActivity::class.java)).also { finishAffinity() }
        }, 3000)
    }
}