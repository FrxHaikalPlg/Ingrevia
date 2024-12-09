package com.frxhaikal_plg.ingrevia.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.frxhaikal_plg.ingrevia.R
import com.frxhaikal_plg.ingrevia.ui.introduction.IntroductionActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            val introShown = getSharedPreferences("app_prefs", MODE_PRIVATE)
                .getBoolean("intro_shown", false)
            
            val intent = if (introShown) {
                Intent(this, LoginActivity::class.java)
            } else {
                Intent(this, IntroductionActivity::class.java)
            }
            startActivity(intent)
            finish()
        }, 1500)
    }
}
