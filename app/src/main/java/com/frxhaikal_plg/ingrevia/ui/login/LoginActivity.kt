package com.frxhaikal_plg.ingrevia.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.frxhaikal_plg.ingrevia.MainActivity
import com.frxhaikal_plg.ingrevia.R
import com.frxhaikal_plg.ingrevia.data.local.UserPreferences
import com.frxhaikal_plg.ingrevia.databinding.ActivityLoginBinding
import com.frxhaikal_plg.ingrevia.ui.RegisterActivity
import com.frxhaikal_plg.ingrevia.ui.introduction.IntroductionActivity
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        userPreferences = UserPreferences(this)
        
        setupButtons()
    }
    
    private fun setupButtons() {
        binding.backArrow.setOnClickListener {
            startActivity(Intent(this, IntroductionActivity::class.java))
            finish()
        }

        binding.btnLogin.setOnClickListener {
            if (isInputValid()) {
                handleLogin()
            } else {
                Toast.makeText(this, getString(R.string.please_complete_login), Toast.LENGTH_SHORT).show()
            }
        }
        
        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.forgotPass.setOnClickListener {
            // TODO: Implementasi forgot password
        }

        binding.googleButton.setOnClickListener {
            handleGoogleSignIn()
        }
    }
    
    private fun isInputValid(): Boolean {
        return binding.edLoginEmail.error == null && 
               binding.edLoginPassword.error == null &&
               binding.edLoginEmail.text?.isNotEmpty() == true &&
               binding.edLoginPassword.text?.isNotEmpty() == true
    }
    
    private fun handleLogin() {
        setLoading(true)
        lifecycleScope.launch {
            try {
                // TODO: Implementasi login ke API
                userPreferences.setLoggedIn(true)
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                finishAffinity()
            } catch (e: Exception) {
                Toast.makeText(
                    this@LoginActivity, 
                    getString(R.string.login_failed, e.message), 
                    Toast.LENGTH_SHORT
                ).show()
            } finally {
                setLoading(false)
            }
        }
    }

    private fun handleGoogleSignIn() {
        setLoading(true)
        // TODO: Implementasi Google Sign In
        setLoading(false)
    }

    private fun setLoading(isLoading: Boolean) {
        binding.loading.loadingOverlay.visibility = if (isLoading) View.VISIBLE else View.GONE
        if (isLoading) {
            binding.loading.loadingText.text = getString(R.string.logging_in)
        }
        binding.btnLogin.isEnabled = !isLoading
        binding.googleButton.isEnabled = !isLoading
        binding.edLoginEmail.isEnabled = !isLoading
        binding.edLoginPassword.isEnabled = !isLoading
    }
}