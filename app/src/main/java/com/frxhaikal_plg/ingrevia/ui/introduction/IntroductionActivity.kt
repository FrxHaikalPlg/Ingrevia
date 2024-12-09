package com.frxhaikal_plg.ingrevia.ui.introduction

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.frxhaikal_plg.ingrevia.R
import com.frxhaikal_plg.ingrevia.ui.LoginActivity

class IntroductionActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_introduction)
        
        viewPager = findViewById(R.id.viewPager)
        
        val fragments = listOf(
            Introduction1Fragment().apply {
                setOnNextClickListener { viewPager.currentItem = 1 }
                setOnSkipClickListener { navigateToLogin() }
            },
            Introduction2Fragment().apply {
                setOnNextClickListener { viewPager.currentItem = 2 }
                setOnSkipClickListener { navigateToLogin() }
            },
            Introduction3Fragment().apply {
                setOnNextClickListener { navigateToLogin() }
                setOnSkipClickListener { navigateToLogin() }
            }
        )
        
        val adapter = IntroductionPagerAdapter(this, fragments)
        viewPager.adapter = adapter
        
        // Disable swiping
        viewPager.isUserInputEnabled = false
    }
    
    private fun navigateToLogin() {
        getSharedPreferences("app_prefs", MODE_PRIVATE)
            .edit()
            .putBoolean("intro_shown", true)
            .apply()
            
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}
